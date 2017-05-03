DROP TABLE Credentials;
Drop Table Users;

CREATE TABLE Users 
(
User_Id int NOT NULL AUTO_INCREMENT,
LastName varchar(255) NOT NULL,
FirstName varchar(255) NOT NULL,
Image BLOB,
PRIMARY KEY (User_Id)
);

CREATE TABLE Credentials
(
Credential_Id int NOT NULL AUTO_INCREMENT,
Username varchar(255) NOT NULL,
Password varchar(255) NOT NULL,
Url varchar(1024) NOT NULL,
User_Id int NOT NULL,
PRIMARY KEY (Credential_Id),
FOREIGN KEY (User_Id) REFERENCES Users(User_Id)
);