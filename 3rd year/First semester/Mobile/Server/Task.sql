CREATE TABLE Task(
	id int PRIMARY KEY IDENTITY (1, 1),
	name varchar(50),
	info varchar(100),
	duration int,
	isDone BIT 
)


INSERT INTO Task values ('Task 1', 'info', 2, 1);
INSERT INTO Task values ('Task 2', 'info', 4, 1);
INSERT INTO Task values ('Task 3', 'info', 1, 0);
INSERT INTO Task values ('Task 4', 'info', 5, 0);
INSERT INTO Task values ('Task 5', 'info', 4, 1);
INSERT INTO Task values ('Task 6', 'info', 3, 1);
INSERT INTO Task values ('Task 7', 'info', 1, 0);
INSERT INTO Task values ('Task 8', 'info', 1, 1);
INSERT INTO Task values ('Task 9', 'info', 4, 0);
INSERT INTO Task values ('Task 10', 'info', 3, 1);