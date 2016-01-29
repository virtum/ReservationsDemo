// config for h2 db :

org.h2.Driver
jdbc:h2:~/reservations
*** - ***

// Sql for table users : 

CREATE TABLE users
(
Id int NOT NULL,
FirstName varchar(255)NOT NULL,
LastName varchar(255) NOT NULL,
Email varchar(255) NOT NULL,
Password varchar(255)NOT NULL,
UNIQUE (Email)
)

// sql for parking :

CREATE TABLE parking
(
Id int NOT NULL,
ParkingName varchar(255) NOT NULL,
PlaceNumber int NOT NULL,
IsFree BOOLEAN NOT NULL, 
OwnerEmail varchar(255)
) 

// sql instert values to parkings : 

INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (1, 'a' ,1, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (2, 'a', 2, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (3, 'a', 3, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (4, 'a',4, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (5, 'a', 5, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (6, 'a', 6, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (7, 'a', 7, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (8, 'a', 8, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (9, 'a',9, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (10, 'a',10, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (11, 'a', 11, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (12, 'a', 12, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (13, 'a', 13, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (14, 'a', 14, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (15, 'a', 15, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (16, 'a', 16, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (17, 'a', 17, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (18, 'a', 18, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (19, 'a', 19, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (20, 'a', 20, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (21, 'b', 1, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (22, 'b', 2, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (23, 'b', 3, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (24, 'b', 4, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (25, 'b', 5, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (26, 'b', 6, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (27, 'b', 7, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (28, 'b', 8, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (29, 'b', 9 ,true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (30, 'b', 10, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (31, 'b', 11, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (32, 'b', 12, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (33, 'b', 13, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (34, 'b', 14, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (35, 'b', 15, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (36, 'b', 16, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (37, 'b', 17, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (38, 'b', 18 , true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (39, 'b', 19, true);
INSERT INTO parking (id, parkingname, PlaceNumber, isfree)
VALUES (40 , 'b', 20, true);

// Book table sql:

CREATE TABLE book
(
Id int NOT NULL,
Title varchar(255) NOT NULL,
Author varchar(255) NOT NULL,
IsFree BOOLEAN NOT NULL, 
OwnerEmail varchar(255)
) 

// sql instert values to book : 

INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (1, 'Jêzyk C++. Szko³a programowania ', 'Prata Stephen', true, null);	
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (2, 'Czysty kod. Podrêcznik dobrego programisty', 'Robert C. Martin', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (3, 'Spring w akcji', 'Walls Craig', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (4, 'Thinking in Java. Edycja polska', 'Eckel Bruce', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (5, 'Programowanie równoleg³e i asynchroniczne w C# 5.0 ', 'Opracowanie zbiorowe', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (6, 'Android Studio. Podstawy tworzenia aplikacji ', 'Stasiewicz Andrzej', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (7, 'Java 8. Przewodnik doœwiadczonego programisty', 'Horstmann Cay S.', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (8, 'Algorytmy. Struktury danych i techniki programowania', 'Wróblewski Piotr', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (9, 'C#. Rusz g³ow¹!', 'Greene Jennifer, Stellman Andrew', true, null);
INSERT INTO book(id, title, author,  isfree, ownerEmail)
VALUES (10, 'Wzorce projektowe. Rusz g³ow¹!', 'Freeman Elisabeth, Freeman Eric, Bates Bert, Sierra Kathy', true, null);
	
	
//sql for participant:

create table participants(
   id INT NOT NULL,
   participantemail VARCHAR(255) not NULL,
   idx INT default NULL,
   trainingid int default Null
);


sql for training:

create table training (
   id INT NOT NULL ,
   title VARCHAR(255) not NULL,
   lecturer  VARCHAR(255) not NULL,
   description VARCHAR(1000) not NULL,
   TRAININGDATE date not null,
   seats INT  not NULL
);

// sql insert code for training:

INSERT INTO training(id, title, lecturer, description, trainingdate, seats)
VALUES (1, 'Basics of Programming with JavaScript', 'Kyle Simpson' , 'Want to learn to program and use JavaScript as the language of exploration? Great idea! JavaScript is uniquely one of the easiest languages to get started with and one of the most powerful. It will challenge you to learn more even years or decades after you start.', '2015-12-16', 20);

INSERT INTO training(id, title, lecturer, description, trainingdate, seats)
VALUES (2, 'REST Fundamentals', 'Howard Dierking', 'REST is an overloaded, and thus misunderstood term in architectural circles these days. This course attempts to clear up some of the misunderstandings about REST as well as provide a more practical approach for designing RESTful solutions - both clients and services. Additionally, the course looks at REST from the perspective of the cloud and describes how REST is well-suited to meet the demands that the cloud brings to bear on a modern architecture.', '2015-11-08', 30); 

INSERT INTO training(id, title, lecturer, description, trainingdate, seats)
VALUES (3, 'Introduction to Testing in Java', 'Richard Warburton', 'This course introduces why you want to write automated tests for your code and how to implement this in Java, covering fundamentals of how to write simple tests using JUnit and Hamcrest, through Test Driven Development (TDD) and then explains how to structure your code and design in order to make testing easier.', '2016-01-05', 30); 

INSERT INTO training(id, title, lecturer, description, trainingdate, seats)
VALUES (4, 'Git Fundamentals', 'James Kovacs', 'Git is a popular distributed version control system (DVCS). In this course, learn how to create a local repository, commit files, push changes to a remote repository, fix errors in your commits, and many of Gits other features. Understand the difference between the working copy, staging area, and repository itself. Come learn the power of Git.', '2016-02-14', 20);

INSERT INTO training(id, title, lecturer, description, trainingdate, seats)
VALUES (5, 'Design Patterns in Java: Structural', 'Bryan Hansen', 'This course is part of a three-part series covering design patterns using Java. This part covers structural design patterns such as Adapter, Bridge, Composite, Decorator, Facade, Flyweight, Proxy as defined by the Gang of Four. We look at examples in the Java API and code examples of each pattern.', '2016-01-25', 30);


