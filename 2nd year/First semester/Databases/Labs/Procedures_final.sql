
CREATE TABLE Voicemails(
	id INT NOT NULL,
	recv_from VARCHAR(55) NOT NULL,
	CONSTRAINT PK_Id PRIMARY KEY (id)
);

ALTER TABLE Voicemails
ADD CONSTRAINT FK_recv_id
FOREIGN KEY (id) REFERENCES Contacts(id);
--A

DROP PROCEDURE modifyColumn;
DROP PROCEDURE reverse_modifyColumn;

CREATE OR ALTER PROCEDURE modifyColumn
AS
BEGIN
	ALTER TABLE Address_book
	ALTER COLUMN building_nr float
	PRINT 'Column building_nr from Address_book has now type float'
END
GO

CREATE OR ALTER PROCEDURE reverse_modifyColumn
AS
BEGIN
	ALTER TABLE Address_book
	ALTER COLUMN building_nr int
	PRINT 'Column building_nr from Address_book has now type int'
END
GO

EXEC modifyColumn
EXEC reverse_modifyColumn

--B
CREATE OR ALTER PROCEDURE removeColumn
AS
BEGIN
	ALTER TABLE Address_book
	DROP COLUMN county
	PRINT 'Column county from Address_book has been removed'
END
GO

CREATE OR ALTER PROCEDURE addColumn
AS
BEGIN
	ALTER TABLE Address_book
	ADD county VARCHAR(55)
	PRINT 'Column county for Address_book has been added'
END
GO

EXEC removeColumn
EXEC addcolumn

--C
CREATE OR ALTER PROCEDURE addDefaultConstraint
AS
BEGIN
	ALTER TABLE Email_address
	ADD CONSTRAINT d_email DEFAULT '' FOR email_address
	PRINT 'Default constraint for column email_address from Email_address has been added'
END
GO

CREATE OR ALTER PROCEDURE removeDefaultConstraint
AS
BEGIN
	ALTER TABLE Email_address
	DROP CONSTRAINT d_email 
	PRINT 'Default constraint for column email_address from Email_address has been removed'
END
GO

EXEC addDefaultConstraint
EXEC removeDefaultConstraint

--D

CREATE OR ALTER PROCEDURE removePrimaryKey
AS
BEGIN
	ALTER TABLE Voicemails
	DROP CONSTRAINT Pk_id
	PRINT 'Primary key from table Voicemails has been removed'
END
GO

CREATE OR ALTER PROCEDURE addPrimaryKey
AS
BEGIN
	ALTER TABLE Voicemails
	ADD CONSTRAINT Pk_id PRIMARY KEY (id)
	PRINT 'Primary key for table Voicemails has been added'
END
GO

EXEC removePrimaryKey
EXEC addPrimaryKey

--E
CREATE OR ALTER PROCEDURE addCandidateKey
AS 
BEGIN
	ALTER TABLE Contacts
	ADD CONSTRAINT c_phone_number UNIQUE (phone_number)
	PRINT 'Candidate key added for phone_number from Contacts'
END
GO

CREATE OR ALTER PROCEDURE removeCandidateKey
AS 
BEGIN
	ALTER TABLE Contacts
	DROP CONSTRAINT c_phone_number 
	PRINT 'Candidate key removed from phone_number from Contacts'
END
GO

EXEC addCandidateKey
EXEC removeCandidateKey

--F
CREATE OR ALTER PROCEDURE removeForeignKey
AS
BEGIN
	ALTER TABLE Voicemails
	DROP CONSTRAINT FK_recv_id
	PRINT 'Foreign key for recv_id from Voicemails has been removed'
END
GO

CREATE OR ALTER PROCEDURE addForeignKey
AS
BEGIN
	ALTER TABLE Voicemails
	ADD CONSTRAINT FK_recv_id
	FOREIGN KEY (id) REFERENCES Contacts(id)
	PRINT 'Foreign key for recv_id from Voicemails has been added'
END
GO

EXEC removeForeignKey
EXEC addForeignKey

--G

CREATE OR ALTER PROCEDURE removeTable
AS
BEGIN
	DROP TABLE IF EXISTS Groups_members
	PRINT 'Table Groups_members has been removed'
END
GO

CREATE OR ALTER PROCEDURE addTable
AS
BEGIN
	CREATE TABLE Groups_members(
	id INT NOT NULL PRIMARY KEY,
	member INT FOREIGN KEY REFERENCES Contacts(id),
	group_name INT FOREIGN KEY REFERENCES Groups(id)
	);
	PRINT 'Table Groups_members has been added'
END
GO

EXEC removeTable
EXEC addTable

DROP TABLE Versions

CREATE TABLE Versions(
	databaseVersion int unique not null,
	procedureName nvarchar(50) default '',
	undo_procedureName nvarchar(50) default '',
	currentVersion int default 0,
)
GO
INSERT INTO Versions (databaseVersion, currentVersion) VALUES (0, 1)
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (1, 'modifyColumn', 'reverse_modifyColumn');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (2, 'removeColumn', 'addColumn');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (3, 'addDefaultConstraint', 'removeDefaultConstraint');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (4, 'removePrimaryKey', 'addPrimaryKey');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (5, 'addCandidateKey', 'removeCandidateKey');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (6, 'removeForeignKey', 'addForeignKey');
INSERT INTO Versions (databaseVersion, procedureName, undo_procedureName) VALUES (7, 'removeTable', 'addTable');
GO

CREATE OR ALTER PROCEDURE main(@givenVersion INT) AS
BEGIN
	-- IF the required version is not one of [0,1,2..., 7], throw an error. 
	IF @givenVersion < 0 OR @givenVersion > 7
		BEGIN
			RAISERROR ('You requested an invalid version', 10, 1)
		END
	ELSE
		BEGIN
		DECLARE @currentVersion INT
		SELECT @currentVersion=databaseVersion
		FROM Versions V
		WHERE V.currentVersion = 1

		PRINT @currentVersion
		-- if the required version is the same as the current version, send a notification.
		IF @currentVersion = @givenVersion
			BEGIN
				RAISERROR ('The version you requested is the current version', 10, 1)
			END
		ELSE
			BEGIN
				UPDATE Versions SET currentVersion=0 WHERE databaseVersion=@currentVersion
				IF @currentVersion < @givenVersion
					BEGIN
						WHILE @currentVersion < @givenVersion
							BEGIN
								DECLARE @procedureName nvarchar(50)
								SET @currentVersion = @currentVersion + 1
								-- get the procedure of the current version
								SELECT @procedureName=procedureName 
								FROM Versions
								WHERE databaseVersion=@currentVersion
								-- execute the procedure
								EXEC @procedureName
							END
					END
				ELSE
					BEGIN
						WHILE @currentVersion > @givenVersion
							BEGIN
								DECLARE @undoProcedureName nvarchar(50)
								-- get the undo procedure of the current version
								SELECT @undoProcedureName = undo_procedureName 
								FROM Versions
								WHERE databaseVersion = @currentVersion
								SET @currentVersion = @currentVersion - 1
								-- execute the procedure
								EXEC @undoProcedureName
							END
					END
				UPDATE Versions SET currentVersion = 1 where databaseVersion = @givenVersion
			END
		END
END
GO

EXEC main 10