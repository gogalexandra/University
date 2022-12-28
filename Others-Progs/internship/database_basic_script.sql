DROP TABLE Employee;
DROP TABLE User_Role;
DROP TABLE Seat;
DROP TABLE Booked_Seat;

CREATE TABLE User_Role(
	id int PRIMARY KEY IDENTITY (1, 1),
	role_type varchar(15)
)

CREATE TABLE Employee(
	user_code varchar(3) PRIMARY KEY,
	name varchar(25),
	role_type int REFERENCES User_Role(id),
	team_leader_user_code varchar(3) FOREIGN KEY REFERENCES Employee(user_code) NULL
)


CREATE TABLE Seat(
	id int PRIMARY KEY IDENTITY (1, 1),
	code varchar(3),
	is_active BIT 
)

CREATE TABLE Booked_Seat(
	booked_date date,
	seat_id int FOREIGN KEY REFERENCES Seat(id) ON DELETE CASCADE,
	CONSTRAINT PK_Composed_Date_Seat PRIMARY KEY(booked_date, seat_id),
	employee_user_code varchar(3) FOREIGN KEY REFERENCES Employee(user_code) ON DELETE CASCADE,
	--bookedBy varchar(3) FOREIGN KEY REFERENCES Employee(userCode),
	--isDeleted bit DEFAULT 0
)

GO
CREATE TRIGGER On_Employee_TeamLeader_Delete
ON Employee
INSTEAD OF DELETE 
AS
	DECLARE @userCode varchar(3)
	SET @userCode = (select deleted.user_code from deleted)
	IF EXISTS(SELECT user_code FROM Employee WHERE team_leader_user_code in (SELECT deleted.user_code from deleted))
		UPDATE Employee 
		SET team_leader_user_code = null 
	DELETE FROM Employee WHERE user_code like @userCode
GO

INSERT INTO User_Role values('EMPLOYEE');
INSERT INTO User_Role values('TEAM_LEADER');
INSERT INTO User_Role values('ADMIN');


INSERT INTO Employee values ('rcc', 'rares', 2, null);
INSERT INTO Employee values ('yvi', 'vlad', 1, 'rcc');
INSERT INTO Employee values ('ypc', 'paula', 1, 'rcc');
INSERT INTO Employee values ('yag', 'alexandra', 1, 'rcc');
INSERT INTO Employee values ('ygb', 'georgiana', 1, 'rcc');
INSERT INTO Employee values ('yib', 'iur', 1, 'rcc');
INSERT INTO Employee values ('yac', 'andrei', 1, 'rcc');

INSERT INTO Seat values('D1', 1);
INSERT INTO Seat values('D2', 1);
INSERT INTO Seat values('D3', 1);
INSERT INTO Seat values('D4', 1);

INSERT INTO Booked_Seat values ('2022-02-24', 1, 'yag');
INSERT INTO Booked_Seat values ('2022-02-24', 2, 'yvi');
INSERT INTO Booked_Seat values ('2022-02-24', 3, 'ygb');
INSERT INTO Booked_Seat values ('2022-02-24', 4, 'yib');
INSERT INTO Booked_Seat values ('2022-02-25', 1, 'yag');
INSERT INTO Booked_Seat values ('2022-02-25', 2, 'yvi');


--SELECT * FROM Employee;








