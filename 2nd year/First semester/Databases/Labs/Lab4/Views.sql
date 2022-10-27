DROP TABLE Address_book
CREATE TABLE Address_book(
	id INT NOT NULL,
	contact_id INT NOT NULL,
	street VARCHAR(55) NOT NULL,
	building_nr INT NOT NULL,
	city VARCHAR(55) NOT NULL,
	county VARCHAR(55) NOT NULL,
	country VARCHAR(55) NOT NULL
);

ALTER TABLE Address_book
ADD CONSTRAINT Pk_id_adrr PRIMARY KEY (id, contact_id)
GO

ALTER TABLE Address_book
ADD CONSTRAINT FK_c_id
FOREIGN KEY (contact_id) REFERENCES Contacts(id)
GO

ALTER TABLE Address_book
	DROP CONSTRAINT FK_c_id
GO

CREATE TABLE EmergencyContacts(
	id INT NOT NULL,
	first_name VARCHAR(55) NOT NULL,
	last_name VARCHAR(55) NOT NULL,
	phone_number VARCHAR(55) NOT NULL,
	CONSTRAINT Pk_ec_id PRIMARY KEY (id)
);
GO
--Working with EmergencyContacts(primary key and no foreign key)
			-- Voicemails(primary + foreign key)
			-- Addressbook(multicolumn primary key)


--View operarting on one table
CREATE OR ALTER VIEW viewEmContacts AS
	SELECT id, first_name, last_name
	FROM EmergencyContacts
	WHERE LEN(last_name) > 5;
GO

SELECT *
FROM viewEmContacts
GO

--View operationg on 2 tables
CREATE OR ALTER VIEW viewVoicemails AS
	SELECT Contacts.first_name, Contacts.last_name
	FROM Contacts
	RIGHT JOIN Voicemails ON Contacts.id = Voicemails.recv_from
GO

SELECT *
FROM viewVoicemails
GO


--View operating on 2 tables with GROUP BY
CREATE OR ALTER VIEW viewStreets AS
	SELECT A.street, A.building_nr, A.city
	FROM Address_book as A
	GROUP BY A.street, A.building_nr, A.city, A.contact_id 
	HAVING 5 < (SELECT LEN(last_name)
				FROM Contacts as C
				WHERE c.id = A.contact_id);
GO

SELECT *
FROM viewStreets 
GO


CREATE OR ALTER PROC runViewTest(@givenViewName VARCHAR(100)) AS
BEGIN
   DECLARE @runViewTestCommand NVARCHAR(200)
    SET @runViewTestCommand = 'SELECT * FROM ' + @givenViewName
    EXEC @runViewTestCommand
END

CREATE OR ALTER PROC runDeleteTableTest(@givenTable VARCHAR(100), @deletedNrOfRows INT) AS
BEGIN
   DECLARE @runDeleteTableTest NVARCHAR(200)
    SET @runDeleteTableTest = 'DELETE FROM ' + @givenTable + ' WHERE id <= ' + @deletedNrOfRows
    EXEC @runDeleteTableTest
END
