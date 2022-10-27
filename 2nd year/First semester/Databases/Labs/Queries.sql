--- QUERIES

--A

--Date id of days with meetings & activities
SELECT date FROM Meetings
UNION
SELECT date FROM Activities
ORDER BY date;

--busy or full days
SELECT DAY(date_limit) - DAY(GETDATE()) AS DaysLeft, Calendar.date_limit
FROM Calendar
WHERE type_of_day = 'busy' OR type_of_day = 'full';

--B

--Activities in which I have company 
SELECT Activities.id, description, name
FROM Activities
LEFT JOIN Activity_buddies
ON Activities.id = Activity_buddies.activity

INTERSECT

SELECT Activities.id, description, name
FROM Activities
RIGHT JOIN Activity_buddies
ON Activities.id = Activity_buddies.activity;

--Meetings in which Sarah participates
SELECT id, date, location, description
FROM Meetings
WHERE 'Sarah' IN (SELECT Meeting_participants.name FROM Meeting_participants WHERE Meetings.id = Meeting_participants.meeting);

--C
--Contacts without email addresses
SELECT Contacts.id,first_name, last_name, email_address 
FROM Contacts
LEFT JOIN Email_address
ON Contacts.id = Email_address.contact_id

EXCEPT

SELECT Contacts.id, first_name, last_name, email_address 
FROM Contacts
RIGHT JOIN Email_address
ON Contacts.id = Email_address.contact_id;

--Contacts without contact history
SELECT id, first_name, last_name 
FROM Contacts
WHERE id NOT IN (SELECT contact_id FROM Contact_history)
ORDER BY last_name;

--D

--Name of the persons i need to send mail to 
SELECT Mails_to_send.id, Mails_to_send.important_mail_subject, Email_address.email_address
FROM ((send_to
INNER JOIN Email_address ON send_to.send_to = Email_address.id)
INNER JOIN Mails_to_send ON send_to.mail = Mails_to_send.id);

--Show contacts with their address, if exists
SELECT Contacts.first_name, Contacts.last_name, Address_book.street, Address_book.building_nr, Address_book.city
FROM Contacts
RIGHT JOIN Address_book ON Contacts.id = Address_book.contact_id
ORDER BY Address_book.city;


--email adr and the group they migth be
SELECT DISTINCT
  Email_address.email_address,
  Groups.group_name
FROM Email_address
LEFT JOIN send_to
  ON Email_address.id = send_to.send_to
LEFT JOIN Mails_to_send
  ON Mails_to_send.id = send_to.mail
LEFT JOIN Contacts
  ON Contacts.id = Email_address.contact_id
LEFT JOIN Groups_members
  ON Contacts.id = Groups_members.member
LEFT JOIN Groups
  ON Groups.id = Groups_members.group_name;

--times limit with activity/meeting if any 
SELECT DISTINCT
  DAY(date_limit) - DAY(GETDATE()) AS DaysLeft,
  Activities.description AS 'Activity description',
  Meetings.description AS 'Meeting description'
FROM Calendar
FULL JOIN Activities
  ON Activities.date = Calendar.id
FULL JOIN Meetings
  ON Meetings.date = Calendar.id;


--E

--Group members with name not complete
SELECT Contacts.first_name, last_name
FROM Contacts
WHERE Contacts.id IN 
       (SELECT Groups_members.member
       FROM Groups_members
       WHERE Groups_members.member = Contacts.id AND last_name is NULL);


--dates with activities befor december
SELECT TOP 3 Calendar.date_limit , DAY(date_limit) - DAY(GETDATE()) AS DaysLeft
FROM Calendar
WHERE Calendar.id IN
    (SELECT Activities.id
     FROM Activities
     WHERE Activities.id IN
        (SELECT Activity_buddies.id
         FROM Activity_buddies
		 WHERE Calendar.date_limit < '2021.11.15')
    );

--F

--Meetings near me
SELECT Meetings.id, Meetings.description
FROM Meetings
WHERE EXISTS (SELECT Location.distance FROM Location WHERE Location.id = Meetings.location AND distance < 5);

--1to1 meetings
SELECT Meetings.id, Meetings.description
FROM Meetings
WHERE EXISTS (SELECT Meeting_participants.contact_id FROM Meeting_participants WHERE Meetings.id = Meeting_participants.meeting 
			AND (SELECT COUNT(Meeting_participants.id) FROM Meeting_participants WHERE Meetings.id = Meeting_participants.meeting)=1);


--G

--Avrage nr of times i ve made a call to a pers
SELECT AVG(calls) FROM (SELECT SUM(times_contacted) AS calls FROM Contact_history  GROUP BY contact_id) AS t;

--Count of distinct cities 
SELECT COUNT(cities) FROM (SELECT DISTINCT city AS cities FROM Address_book) AS t;

--H

--number of persons from all cities
SELECT COUNT(Address_book.id), Address_book.city
FROM Address_book
GROUP BY city;

--number of calls from oct 20
SELECT SUM(times_contacted), last_time_contacted
FROM Contact_history
GROUP BY last_time_contacted
HAVING last_time_contacted = '2021-10-20';

--id of the group with more than 3 members
SELECT GR.id, GR.group_name
FROM Groups AS GR
GROUP BY GR.id, GR.group_name
HAVING 3 < (SELECT COUNT(Groups_members.id)
			FROM Groups_members 
			WHERE GR.id = Groups_members.group_name);

--days with fewer thigs to do than the avg
SELECT C.id, C.date_limit
FROM Calendar AS C
GROUP BY C.date_limit, C.id
HAVING 
(SELECT COUNT(Activities.date) + COUNT( Meetings.date) + COUNT(Mails_to_send.date)
FROM Activities, Meetings, Mails_to_send
WHERE Activities.date = C.id AND Meetings.date= C.id AND Mails_to_send.date = C.id )
< (SELECT AVG(Activities.date) + AVG( Meetings.date) + AVG(Mails_to_send.date)
FROM Activities, Meetings, Mails_to_send
WHERE Activities.date = C.id AND Meetings.date= C.id AND Mails_to_send.date = C.id );

--I

--persons i ve called more than 5 times

SELECT	TOP 2 Contacts.id, Contacts.first_name, Contacts.last_name
FROM Contacts 
WHERE Contacts.id = ANY
  (SELECT Contact_history.contact_id
  FROM Contact_history
  WHERE times_contacted > 5);

--rewrite with aggregation op

SELECT c.id, c.first_name, c.last_name
FROM Contacts AS c
GROUP BY c.id, c.first_name, c.last_name
HAVING 5 < (SELECT SUM(times_contacted)
			FROM Contact_history as CH
			WHERE c.id = CH.contact_id);

--activities after 15 nov

SELECT Activities.id, Activities.description
FROM Activities 
WHERE Activities.date = ANY
  (SELECT Calendar.id
	FROM Calendar
	WHERE Calendar.date_limit > '2021.11.15');

-- rewrite 
SELECT Activities.id, Activities.description
FROM Activities 
WHERE Activities.date IN (SELECT Calendar.id
FROM Calendar
WHERE Calendar.date_limit > '2021.11.15')


--activies with location further than 5 km
SELECT Activities.id, Activities.description
FROM Activities
WHERE (SELECT distance
FROM Location L
WHERE L.id = Activities.location) > ALL(SELECT distance
FROM Location L
WHERE distance = 5);




--all addresses before the first one from Oradea
SELECT Address_book.id, Address_book.street, Address_book.building_nr
FROM Address_book
WHERE Address_book.building_nr > ALL(SELECT Address_book.building_nr
FROM Address_book
WHERE Address_book.city = 'Oradea');

