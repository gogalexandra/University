
--- INSERT

USE Planner;

INSERT INTO Contacts(id, first_name, last_name, phone_number)
VALUES 
	(1, 'Andrei', 'Gog', '0745123456'),
	(2, 'Simina', 'Pop', '0712377456'),
	(3, 'Anca', 'Gherman', '0745903455'),
	(4, 'Alexandra', 'Muresan', '0799302345'),
	(5, 'Tudor', 'Muresan', '0767892345'),
	(6, 'Andreea', 'Terec', '0712039503'),
	(7, 'Sabina', 'Silaghi', '0712030000'),
	(8, 'George', 'Terec', '07120356573'),
	(9, 'Sarah', 'Vomir', '0713339503'),
	(10, 'Thea', 'Crainic', '0712035563'),
	(11, 'Teo', 'Lung', '0712334503'),
	(12, 'Alex', 'Ursu', '0799358655');

INSERT INTO Address_book(id, contact_id, street, building_nr, city, county, country)
VALUES
	(1, 2, 'Str. Buzau', 9, 'Cluj-Napoca', 'Cluj', 'Romania'),
	(2, 3, 'Str. Corneliu Coposu', 45, 'Cluj-Napoca', 'Cluj', 'Romania'),
	(3, 2, 'Str. Aleea Baita', 2, 'Cluj-Napoca', 'Cluj', 'Romania'),
	(4, 4, 'Str. Dornei', 9, 'Cluj-Napoca', 'Cluj', 'Romania'),
	(5, 1, 'Str. Soarelui', 34, 'Sibiu', 'Sibiu', 'Romania'),
	(6, 5, 'Str. Soseaua Nordului', 1, 'Bucuresti', 'Bucuresti', 'Romania'),
	(7, 5, 'Str. Mugurilor', 9, 'Oradea', 'Bihor', 'Romania'),
	(8, 5, 'Str. Volga', 10, 'Bucuresti', 'Bucuresti', 'Romania');

INSERT INTO Email_address(id, contact_id, email_address)
VALUES
	(1, 1, 'andrei_gog@yahoo.com'),
	(2, 12, 'ursu.a@gmail.com'),
	(3, 6, 'deea_terec@yahoo.com'),
	(4, 1, 'andrei.gog@gmail.com'),
	(5, 2, 'pop_simi22@gmail.com'),
	(6, 5, 'muresan.t@yahoo.com'),
	(7, 4, 'alexandra.ale@gmail.com'),
	(8, 4, 'alexandra_muresan20001@yahoo.com'),
	(9, 3, 'ghermananca@icloud.com'),
	(10, 6, 'terecAndreea@gmail.com'),
	(11, 11, 'teolungu23@gmail.com'),
	(12, 7, 'sabinas@yahoo.com'),
	(13, 6, 'terecAndreea@yahoo.com'),
	(14, 7, 'silaghi.sabina@gmail.com'),
	(15, 6, 'terecAndreea@gmail.com'),
	(16, 9, 'sarahh.vomir@gmail.com'),
	(17, NULL, 'stud@ubbcluj.ro');

INSERT INTO Contact_history(id, contact_id, date_added, last_time_contacted, times_contacted)
VALUES
	(1, 2, '2020-05-03', '2021-10-10', 6),
	(2, 5, '2020-07-19', '2021-10-20', 2),
	(3, 3, '2018-05-03', '2021-10-20', 1),
	(4, 6, '2017-05-03', '2021-08-29', 9),
	(5, 1, '2020-04-22', '2021-10-05', 3),
	(6, 4, '2020-05-12', '2021-03-24', 3),
	(7, 8, '2020-06-06', NULL, 3),
	(8, 12, '2021-05-12', '2021-03-24', 0),
	(9, 9, '2021-01-12', '2021-05-24', 3),
	(10, 11, '2021-06-25', '2021-06-25', 1);

INSERT INTO Groups(id, group_name)
VALUES 
	(1, 'Family'),
	(2, 'Rev 2020'),
	(3, 'Work'),
	(4, 'Friendsss'),
	(5, 'Summer trip'),
	(6, 'Ubb group'),
	(7, 'Weekend plans');

INSERT INTO Groups_members(id, member, group_name)
VALUES
	(1, 1, 1),
	(2, 9, 1),
	(3, 5, 1),
	(4, 4, 1),
	(5, 5, 2),
	(6, 2, 2),
	(7, 6, 2),
	(8, 12, 2),
	(10, 9, 3),
	(11, 12, 3),
	(12, 11, 4),
	(13, 8, 4),
	(14, 10, 4),
	(15, 3, 4),
	(16, 7, 5),
	(17, 12, 5),
	(18, 12, 5),
	(19, 12, 5),
	(20, 3, 4);

INSERT INTO Calendar(id, date_limit, type_of_day)
VALUES
	(1, '2021-11-29', 'free'),
	(2, '2021-12-20', 'free'),
	(3, '2021-11-02', 'full'),
	(4, '2021-11-19', 'busy'),
	(5, '2021-11-13', 'ok'),
	(6, '2021-11-27', 'full'),
	(7, '2021-11-10', 'free');

INSERT INTO Location(id, name, distance, transport_type)
VALUES
	(1, 'Klausen', 2, 'bolt'),
	(2, 'Iulis Park', 5, 'car'),
	(3, 'DaPino', 2, 'walk'),
	(4, 'Work', 3, 'walk'),
	(5, 'Cinema', 1, 'walk'),
	(6, 'Cetatuie', 0, 'walk'),
	(7, 'Univerity', 7, 'bus'),
	(8, 'Lidl', 10, 'car'),
	(9, 'Piata unirii', 3, 'bolt');

INSERT INTO Activities(id, date, location, description)
VALUES
	(1, 1, 1, 'Coffee with mom'),
	(2, 5, 3, 'Lunch date'),
	(3, 5, 8, 'Shopping'),
	(4, 7, 6, 'Walk'),
	(5, 6, 9, 'Meet friends'),
	(6, 4, 5, 'Movie');

INSERT INTO Activity_buddies(id, name, activity)
VALUES
	(1, 'Alex', 5),
	(2, 'Tudor', 2),
	(3, 'Simina', 5),
	(4, 'Thea', 6),
	(5, 'George', 6),
	(6, 'Sarah', 6);

INSERT INTO Meetings(id, date, location, description)
VALUES
	(1, 3, 1, 'Work related'),
	(2, 6, 3, 'Work related'),
	(3, 6, 7, 'University related'),
	(4, 6, 4, 'Work related'),
	(5, 4, 9, 'University related'),
	(6, 4, 3, 'Work related');

INSERT INTO Meeting_participants(id, name, meeting)
VALUES
	(1, 'Boss', 2),
	(2, 'Sarah', 2),
	(3, 'Teacher', 5),
	(4, 'Teacher', 3),
	(5, 'Boss', 4),
	(6, 'Sarah', 6);

INSERT INTO Mails_to_send(id, important_mail_subject, date)
VALUES
	(1, 'About meeting', 2),
	(2, 'School Documents', 2),
	(3, 'Holiday details', 5),
	(4, 'Work papers', 6),
	(5, 'About meeting', 4),
	(6, 'Project', 7);

INSERT INTO send_to(id, send_to, mail)
VALUES
	(1, 16, 1),
	(2, 16, 5),
	(3, 10, 3),
	(4, 6, 3),
	(5, 2, 3),
	(6, 17, 2),
	(7, 12, 4),
	(8, 17, 6);

--- UPDATE

UPDATE Email_address
SET email_address = 'ghermananca@gmail.com'
WHERE email_address LIKE '%@icloud.com';

UPDATE Contact_history
SET last_time_contacted = NULL, times_contacted = 0 
WHERE times_contacted = 0 OR last_time_contacted IS NULL;

UPDATE Location
SET distance += 4
WHERE name IN ('Cinema', 'Lidl', 'Klausen', 'Work');

ALTER TABLE Contacts 
ALTER COLUMN last_name VARCHAR(55) NULL;

UPDATE Contacts
SET last_name = NULL
WHERE id =11;

UPDATE Address_book
SET contact_id = 9
WHERE id = 6;

UPDATE Address_book
SET contact_id = 6
WHERE id = 7;

UPDATE Address_book
SET contact_id = 10
WHERE id = 8;

--- DELETE

DELETE FROM Contact_history
WHERE times_contacted BETWEEN 5 AND 10;

DELETE FROM Groups
WHERE (SELECT COUNT (group_name) FROM Groups_members WHERE group_name = Groups.id ) = 0;


