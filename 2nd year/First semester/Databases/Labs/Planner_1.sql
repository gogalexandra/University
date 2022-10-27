CREATE DATABASE Planner;

USE Planner;

CREATE TABLE Contacts(
	id INT NOT NULL PRIMARY KEY,
	first_name VARCHAR(55) NOT NULL,
	last_name VARCHAR(55) NOT NULL,
	phone_number VARCHAR(55) NOT NULL
);

CREATE TABLE Address_book(
	id INT NOT NULL PRIMARY KEY,
	contact_id INT FOREIGN KEY REFERENCES Contacts(id),
	street VARCHAR(55) NOT NULL,
	building_nr INT NOT NULL,
	city VARCHAR(55) NOT NULL,
	county VARCHAR(55) NOT NULL,
	country VARCHAR(55) NOT NULL
);

CREATE TABLE Email_address(
	id INT NOT NULL PRIMARY KEY,
	contact_id INT FOREIGN KEY REFERENCES Contacts(id),
	email_address VARCHAR(55) NOT NULL
);

CREATE TABLE Contact_history(
	id INT NOT NULL PRIMARY KEY,
	contact_id INT FOREIGN KEY REFERENCES Contacts(id),
	date_added DATE,
	last_time_contacted DATETIME,
	times_contacted INT
);

CREATE TABLE Groups(
	id INT NOT NULL PRIMARY KEY,
	group_name VARCHAR(55),
);

CREATE TABLE Groups_members(
	id INT NOT NULL PRIMARY KEY,
	member_name VARCHAR(55),
	group_name INT FOREIGN KEY REFERENCES Groups(id)
);

CREATE TABLE Calendar(
	id INT NOT NULL PRIMARY KEY,
	date_limit DATETIME,
	type_of_day VARCHAR(55)
);

CREATE TABLE Location(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(55),
	distance INT,
	transport_type VARCHAR(55)
);

CREATE TABLE Meetings(
	id INT NOT NULL PRIMARY KEY,
	date INT FOREIGN KEY REFERENCES Calendar(id),
	location INT FOREIGN KEY REFERENCES Location(id),
	description VARCHAR(55)
);

CREATE TABLE Meeting_participants(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(55),
	meeting INT FOREIGN KEY REFERENCES Meetings(id)
);

CREATE TABLE Activities(
	id INT NOT NULL PRIMARY KEY,
	date INT FOREIGN KEY REFERENCES Calendar(id),
	location INT FOREIGN KEY REFERENCES Location(id),
	description VARCHAR(55)
);

CREATE TABLE Activity_buddies(
	id INT NOT NULL PRIMARY KEY,
	name VARCHAR(55),
	activity INT FOREIGN KEY REFERENCES Activities(id)
);

CREATE TABLE Mails_to_send(
	id INT NOT NULL PRIMARY KEY,
	important_mail_subject VARCHAR(55),
	date INT FOREIGN KEY REFERENCES Calendar(id),
	important_mails_email_id INT FOREIGN KEY REFERENCES email_address(id)
);

CREATE TABLE send_to(
	id INT NOT NULL PRIMARY KEY,
	send_to INT FOREIGN KEY REFERENCES Email_address(id),
	mail INT FOREIGN KEY REFERENCES Mails_to_send(id)

);

DROP TABLE Groups_members;

CREATE TABLE Groups_members(
	id INT NOT NULL PRIMARY KEY,
	member INT FOREIGN KEY REFERENCES Contacts(id),
	group_name INT FOREIGN KEY REFERENCES Groups(id)
);


DROP TABLE send_to;
DROP TABLE Mails_to_send;

CREATE TABLE Mails_to_send(
	id INT NOT NULL PRIMARY KEY,
	important_mail_subject VARCHAR(55),
	date INT FOREIGN KEY REFERENCES Calendar(id)
);

CREATE TABLE send_to(
	id INT NOT NULL PRIMARY KEY,
	send_to INT FOREIGN KEY REFERENCES Email_address(id),
	mail INT FOREIGN KEY REFERENCES Mails_to_send(id)
);

ALTER TABLE Activity_buddies
ADD contact_id INT FOREIGN KEY REFERENCES Contacts(id);

ALTER TABLE Meeting_participants
ADD contact_id INT FOREIGN KEY REFERENCES Contacts(id);

ALTER TABLE Calendar
ALTER COLUMN date_limit DATE;