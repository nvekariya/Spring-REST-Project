DROP SCHEMA IF EXISTS `librarydb`; 
CREATE SCHEMA `librarydb` ;
use `librarydb`;


delimiter $$

CREATE TABLE `BookCatalog` (
  `Bookid` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `Author` varchar(45) DEFAULT NULL,
  `Publisher` varchar(45) DEFAULT NULL,
  `Isbn` bigint(20) NOT NULL,
  `Edition` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `currentstock` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bookid`),
  UNIQUE KEY `Title_UNIQUE` (`Title`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `Studentbookstatus` (
  `Userid` int(11) NOT NULL,
  `Bookid` int(11) NOT NULL,
  `Loandate` datetime NOT NULL,
  `Returndate` datetime DEFAULT NULL,
  PRIMARY KEY (`Userid`,`Bookid`,`Loandate`),
  KEY `User_id_idx` (`Userid`),
  KEY `FK_Book_id_idx` (`Bookid`),
  CONSTRAINT `FK_BookId` FOREIGN KEY (`Bookid`) REFERENCES `BookCatalog` (`Bookid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_User_id` FOREIGN KEY (`Userid`) REFERENCES `User` (`Userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$

delimiter $$

CREATE TABLE `User` (
  `Userid` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Zipcode` int(11) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `UserRole` varchar(15) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`Userid`),
  UNIQUE KEY `User_Name_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1$$



