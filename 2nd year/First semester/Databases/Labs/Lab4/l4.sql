
--Choosing tables

DROP TABLE IF EXISTS EmergencyContacts;

--Table with primary key
CREATE TABLE EmergencyContacts(
	id INT NOT NULL,
	first_name VARCHAR(55) NOT NULL,
	last_name VARCHAR(55) NOT NULL,
	phone_number VARCHAR(55) NOT NULL,
	CONSTRAINT Pk_ec_id PRIMARY KEY (id)
);
GO


DROP TABLE IF EXISTS EmergencyMeetings;
--Table with primary and foreign key
CREATE TABLE EmergencyMeetings(
	id INT NOT NULL,
	time DATETIME NOT NULL,
	location VARCHAR(55) NOT NULL,
	howsComing INT NOT NULL,
	CONSTRAINT Pk_c_id PRIMARY KEY (id)
);
GO

ALTER TABLE EmergencyMeetings
	ADD CONSTRAINT FK_con_id
	FOREIGN KEY (howsComing) REFERENCES EmergencyContacts(id)
GO

ALTER TABLE EmergencyMeetings
	DROP CONSTRAINT FK_c_id
GO

ALTER TABLE EmergencyMeetings
	ALTER COLUMN time DATE
GO

--Multicolumn primary key table
ALTER TABLE Address_book
	DROP CONSTRAINT FK_c_id
GO

--Creating views

CREATE OR ALTER VIEW viewAdrBook AS(
	SELECT * 
	FROM Address_book
);
GO

CREATE OR ALTER VIEW viewMeetingPers AS(
	SELECT first_name, last_name
	FROM EmergencyContacts
	WHERE id = (SELECT howsComing
				FROM EmergencyMeetings)
);
GO

CREATE OR ALTER VIEW viewMeeting AS(
	SELECT M.id, M.location, M.time
	FROM EmergencyMeetings as M
	GROUP BY M.id, M.location, M.time, M.howsComing
	HAVING 5 < (SELECT LEN(last_name)
				FROM EmergencyContacts as C
				WHERE c.id = M.howsComing)
);
GO

CREATE OR ALTER PROC viewProc(@givenViewName NVARCHAR(100)) AS
BEGIN
 
	DECLARE @runViewTestCommand NVARCHAR(200)
    SET @runViewTestCommand = 'SELECT * FROM ' + @givenViewName
    EXEC (@runViewTestCommand)
END
GO


CREATE OR ALTER PROCEDURE insertValues(@givenTable VARCHAR(100), @rows INT) AS
	BEGIN
    -- using a counter to insert the correct number of rows
    DECLARE @count INT
    SET @count = 1

    -- declaring the variables used for the insertion of data into the table EmergencyContacts
    DECLARE @cId INT
    DECLARE @firstName VARCHAR(55)
    DECLARE @lastName VARCHAR(55)
	DECLARE @phoneNumber VARCHAR(55)

    -- declaring the variables used for the insertion of data into the table EmergencyMeetings
    DECLARE @mId INT
    DECLARE @time DATE
    DECLARE @location VARCHAR(55)
    DECLARE @contact INT

    -- declaring the variables used for the insertion of data into the table Address_book
    DECLARE @aId INT
    DECLARE @contactId INT
    DECLARE @street VARCHAR(55)
	DECLARE @nr INT
	DECLARE @city VARCHAR(55)
	DECLARE @county VARCHAR(55)
	DECLARE @country VARCHAR(55)

    WHILE @count <= @rows
        BEGIN
            IF @givenTable = 'EmergencyContacts'
            BEGIN
				SET @cId = @count
				SET @firstName = 'FirstName' + CONVERT(VARCHAR(55), @count)
				SET @lastName = 'LastName' + CONVERT(VARCHAR(55), @count)
				SET @phoneNumber = '071342345' + CONVERT(VARCHAR(55), @count)
             
                INSERT INTO EmergencyContacts(id, first_name, last_name, phone_number) VALUES (@cId, @firstName, @lastName, @phoneNumber)
            END

            IF @givenTable = 'EmergencyMeetings'
            BEGIN
                SET @mId = @count
				SET @time = DATEADD(day, (ABS(CHECKSUM(NEWID())) % 65530), 0)
				SET @location = 'Locations' + CONVERT(VARCHAR(55), @count) 
				SET @contact = (SELECT TOP 1 id FROM EmergencyContacts order by NEWID())
                INSERT INTO EmergencyMeetings(id, time, location, howsComing) VALUES (@mId, @time, @location, @contact)
            END

            IF @givenTable = 'Address_book'
            BEGIN
                SET @aId = @count
				SET @contactId = @count
				SET @street = 'Street' + CONVERT(VARCHAR(55), @count) 
				SET @nr = @count
				SET @city = 'City' + CONVERT(VARCHAR(55), @count)
				SET @county = 'County' + CONVERT(VARCHAR(55), @count)
				SET @country = 'Country' + CONVERT(VARCHAR(55), @count)
                INSERT INTO Address_book(id, contact_id,street, building_nr, city, county, country ) VALUES (@aId, @contactId, @street, @nr, @city, @county, @country)
            END

            SET @count = @count + 1
        END
END
GO

CREATE OR ALTER PROCEDURE deleteValues(@givenTable NVARCHAR(100), @deletedNrOfRows INT) AS
BEGIN
   DECLARE @runDeleteTableTest NVARCHAR(200)
   DECLARE @count INT
   SET @count = 1

   WHILE @count <= @deletedNrOfRows
       BEGIN
           SET @runDeleteTableTest = 'DELETE TOP (1) FROM ' + @givenTable
           EXEC (@runDeleteTableTest)

           SET @count = @count + 1
        END
END
GO 

CREATE OR ALTER PROC linkTablesToTests(@givenTestName VARCHAR(50), @givenTableName VARCHAR(50), @nrOfRows INT, @position INT) AS
BEGIN
    IF @givenTestName NOT IN (SELECT Name FROM Tests) BEGIN
            PRINT 'Name does not exist in Tests'
            RETURN
    END

    IF @givenTableName NOT IN (SELECT Name FROM Tables) BEGIN
        PRINT 'Name does not exist in Tables'
        RETURN
    END

    IF EXISTS(SELECT * FROM TestTables JOIN dbo.Tests T on TestTables.TestID = T.TestID WHERE T.Name = @givenTestName AND TestTables.Position = @position) BEGIN
        PRINT ('Incorrect given position, conflicting with the already existing ones')
        RETURN
    end

    INSERT INTO TestTables (TestID, TableID, NoOfRows, Position) VALUES (
        (SELECT TestID FROM Tests WHERE Name = @givenTestName),
        (SELECT TableID FROM Tables WHERE NAME = @givenTableName),
        @nrOfRows,
        @position
        )
END
GO

CREATE OR ALTER PROC linkViewsToTests(@givenTestName VARCHAR(50), @givenViewName VARCHAR(50)) AS
BEGIN
    IF @givenTestName NOT IN (SELECT Name FROM Tests) BEGIN
            PRINT 'Name does not exist in Tests'
            RETURN
    END

    IF @givenViewName NOT IN (SELECT Name FROM Views) BEGIN
        PRINT 'Name does not exist in Views'
        RETURN
    END

    INSERT INTO TestViews (TestID, ViewID) VALUES (
        (SELECT TestID FROM Tests WHERE Name = @givenTestName),
        (SELECT ViewID FROM Views WHERE Name = @givenViewName)
        )
END
GO


INSERT INTO Views VALUES ('viewAdrBook'), ('viewMeetingPers'), ('viewMeeting')
GO

INSERT INTO Tables VALUES ('EmergencyContacts'), ('EmergencyMeetings'), ('Address_book')
GO

INSERT INTO Tests VALUES ('test1'), ('test2'), ('test3')
GO


EXEC insertValues EmergencyContacts, 100
GO
EXEC insertValues EmergencyMeetings, 100
GO
EXEC insertValues Address_book, 100
GO

-- creating test1
EXEC linkTablesToTests test1, EmergencyContacts, 1000, 1
GO
EXEC linkTablesToTests test1, EmergencyMeetings, 1000, 2
GO
EXEC linkViewsToTests test1, viewMeeting
GO

-- creating test2
EXEC linkTablesToTests test2, Address_book, 1000, 1
GO
EXEC linkTablesToTests test2, EmergencyMeetings, 1000, 2
GO
EXEC linkViewsToTests test2, viewMeetingPers
GO

-- creating test3
EXEC linkTablesToTests test3, EmergencyContacts, 1000, 1
GO
EXEC linkViewsToTests test3, viewMeeting
GO
EXEC linkViewsToTests test3, viewMeetingPers
GO

CREATE OR ALTER PROC runTest(@givenTestName VARCHAR(50)) AS
BEGIN
    IF @givenTestName NOT IN(SELECT Name FROM Tests) BEGIN
        PRINT('Name does not exist in Tests')
        RETURN
    END

    -- the time stamps used for determining the test's time of execution
    DECLARE @startAllTime datetime2
    DECLARE @endAllTime datetime2
    DECLARE @startTime datetime2
    DECLARE @endTime datetime2

    -- the variables used to store the tables/view/rows/position
    DECLARE @tableName VARCHAR(50)
    DECLARE @viewName VARCHAR(50)
    DECLARE @nrOfRows INT
    DECLARE @position INT

    -- the id og the testRun
    DECLARE @testRunId INT
	SET @testRunId = (SELECT MAX(TestRunId) + 1 from TestRuns)
	IF @testRunId IS NULL
		SET @testRunId = 0

    -- the id of the test
    DECLARE @testId INT
    SELECT @testId = TestID FROM Tests WHERE Name = @givenTestName

    -- a cursor used for parsing the tables used in the test
    DECLARE tableCursor CURSOR SCROLL
        FOR
            SELECT Tables.Name, TT.NoOfRows, TT.Position
            FROM Tables JOIN dbo.TestTables TT on Tables.TableID = TT.TableID
            WHERE TT.TestID = @testId
            ORDER BY TT.Position

    --a cursor used for parsing the views used in the test
    DECLARE viewCursor CURSOR
        FOR
            SELECT Views.Name
            FROM Views JOIN dbo.TestViews TV on Views.ViewID = TV.ViewID
            WHERE TV.TestID = @testId

    -- setting the timestamp for the start of the test
    SET @startAllTime = SYSDATETIME()

    -- insert the data regarding the entire test into TestRuns
    SET IDENTITY_INSERT TestRuns ON
    INSERT INTO TestRuns(TestRunID, description, startat)VALUES (@testRunId, 'Running ' + @givenTestName, @startAllTime)
    SET IDENTITY_INSERT TestRuns OFF

    -- perform the deletes
    OPEN tableCursor
    FETCH LAST FROM tableCursor INTO @tableName, @nrOfRows, @position
    WHILE @@FETCH_STATUS = 0 BEGIN
        EXEC deleteValues @tableName, @nrOfRows
        FETCH PRIOR FROM tableCursor INTO @tableName, @nrOfRows, @position
    END
    CLOSE tableCursor

    -- perform the inserts
    OPEN tableCursor
    FETCH FIRST FROM tableCursor INTO @tableName, @nrOfRows, @position
    WHILE @@FETCH_STATUS = 0 BEGIN
        SET @startTime = SYSDATETIME()
        EXEC InsertValues @tableName, @nrOfRows
        SET @endTime = SYSDATETIME()
        -- insert the collected data about the inserts into the TestRunTables
        INSERT INTO TestRunTables(testrunid, tableid, startat, endat) VALUES (@testRunId, (SELECT TableID FROM Tables WHERE Name = @tableName), @startTime, @endTime)
        FETCH NEXT FROM tableCursor INTO @tableName, @nrOfRows, @position
    END
    CLOSE tableCursor
    DEALLOCATE tableCursor

    -- perform the views
    OPEN viewCursor
    FETCH NEXT FROM viewCursor INTO @viewName
    WHILE @@FETCH_STATUS = 0 BEGIN
        SET @startTime = SYSDATETIME()
        EXEC viewProc @viewName
        SET @endTime = SYSDATETIME()
        -- insert the collected data about the view into the TestRunViews
        INSERT INTO TestRunViews(testrunid, viewid, startat, endat) VALUES (@testRunId, (SELECT ViewID FROM Views WHERE Name = @viewName), @startTime, @endTime)
        FETCH NEXT FROM viewCursor INTO @viewName
    END
    CLOSE viewCursor
    DEALLOCATE viewCursor

    -- setting the timestamp for the end of the test
    SET @endAllTime = SYSDATETIME()

    -- updating the endTime for the TestRun
    UPDATE TestRuns
    SET EndAt = @endAllTime
    WHERE TestRunID = @testRunId

END
GO

-- running the tests
EXEC runTest 'test1'
GO
EXEC runTest 'test2'
GO
EXEC runTest 'test3'
GO

SELECT * FROM TestRuns
SELECT * FROM TestRunTables
SELECT * FROM TestRunViews