 /* create database if not exist */
CREATE DATABASE IF NOT EXISTS paradisedb;

/* use database */
USE paradisedb;

/* create admin table */ 
CREATE TABLE IF NOT EXISTS `admin` (
  `id` INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `status` ENUM ('active','inactive') NOT NULL DEFAULT 'active'
)ENGINE=InnoDB;

/* insert into admin */
INSERT INTO `admin` (`username`,`password`) VALUES ('preet','preet');
INSERT INTO `admin` (`username`,`password`) VALUES ('ujjawal','ujjawal');
INSERT INTO `admin` (`username`,`password`) VALUES ('rex','rex');

/* create customers table */
CREATE TABLE IF NOT EXISTS `customers` (
  `custid` INT(7) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `firstname` VARCHAR(255) NOT NULL,
  `lastname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR (255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

/* insert into customers */
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Preet','Acharya','ipreet91@gmail.com','6479714301','preet','preet');
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Nirmoh','Nagwadiya','nirmoh@gmail.com','6479714302','nirmoh','nirmoh');
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Ujjawal','Tivari','ujjawal.tivari@gmail.com','6479714303','ujjawal','ujjawal');
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Hemal','Patel','hemal@gmail.com','6479714304','hemal','hemal');
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Qian','Feng','quian.feng@gmail.com','6479714305','rex','rex');
INSERT INTO `customers` (`firstname`,`lastname`,`email`,`phone`,`username`,`password`) 
VALUES ('Saumil','Surati','saumil@gmail.com','6479714305','saumil','saumil');

/* create roomtypes table */
CREATE TABLE IF NOT EXISTS `roomtypes` (
  `room_type_id` INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `roomtype` VARCHAR(255) NOT NULL,
  `roomdetails` VARCHAR(255) NOT NULL,
  `price` DOUBLE NOT NULL
)ENGINE=InnoDB;

/* insert data into room types */
INSERT INTO `roomtypes` (`roomtype`,`roomdetails`,`price`) 
VALUES ('Mini Suite','A single room with a bed and sitting area','400.00');
INSERT INTO `roomtypes` (`roomtype`,`roomdetails`,`price`) VALUES ('Suite','A parlour or living room 
connected with two bedrooms','700.00');
INSERT INTO `roomtypes` (`roomtype`,`roomdetails`,`price`) VALUES ('Villa','It includes three bedrooms 
with hall and kitchen','1000.00');

/* create status table */
CREATE TABLE IF NOT EXISTS `roomstatus` (
  `status_id` INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `status` VARCHAR(255) NOT NULL
)ENGINE=InnoDB;

/* insert data to room status */
INSERT INTO `roomstatus` (`status`) VALUES ('Available');
INSERT INTO `roomstatus` (`status`) VALUES ('Reserved');
INSERT INTO `roomstatus` (`status`) VALUES ('Maintainance');
INSERT INTO `roomstatus` (`status`) VALUES ('Not Available');

/* create rooms table */
CREATE TABLE IF NOT EXISTS `rooms` (
  `roomid` INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `room_type_id` INT(3) NOT NULL,
  `roomnumber` INT(5) NOT NULL,
  `floor` INT NOT NULL,
  `status_id` INT(3) NOT NULL,
  
  FOREIGN KEY (room_type_id) REFERENCES roomtypes (room_type_id),
  FOREIGN KEY (status_id) REFERENCES roomstatus (status_id)
)ENGINE=InnoDB;

/* insert data into rooms */
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (3,1001,1,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (3,1002,1,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (3,1003,1,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (3,1004,1,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (3,1005,1,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (1,2001,2,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (1,2002,2,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (1,2003,2,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (1,2004,2,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (1,2005,2,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (2,3001,3,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (2,3002,3,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (2,3003,3,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (2,3004,3,1);
INSERT INTO `rooms` (`room_type_id`,`roomnumber`,`floor`,`status_id`) VALUES (2,3005,3,1);

/* create reservations table */
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservid` INT(7) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `custid` INT(7) NOT NULL,
  `roomid` INT(3) NOT NULL,
  `startdate` DATE NOT NULL,
  `enddate` DATE NOT NULL,
  `specialrequest` VARCHAR(255),
  `status` ENUM ('active','inactive') NOT NULL DEFAULT 'active',
  
  FOREIGN KEY (custid) REFERENCES customers (custid),
  FOREIGN KEY (roomid) REFERENCES rooms (roomid)
)ENGINE=InnoDB;

/* insert into reservations table */
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (1,1,'2017-04-01','2017-04-10','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (1,2,'2017-04-01','2017-04-10','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (2,5,'2017-03-31','2017-04-11','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (4,6,'2017-04-07','2017-04-14','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (4,7,'2017-04-07','2017-04-18','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (3,3,'2017-03-31','2017-04-11','');
INSERT INTO `reservations` (`custid`,`roomid`,`startdate`,`enddate`,`specialrequest`) 
VALUES (3,4,'2017-03-31','2017-04-11','');

/* create payment_details table */
CREATE TABLE IF NOT EXISTS `transaction` (
  `transid` INT(7) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `custid` INT(7) NOT NULL,
  `paymenttype` ENUM('cash','credit','debit') NOT NULL DEFAULT 'credit',
  `price` DOUBLE NOT NULL,
  `cardholder` VARCHAR(255),
  `cardname` VARCHAR(255),
  `cardno` VARCHAR(255),
  `expirydate` VARCHAR(255),
  `status` ENUM ('active','inactive') NOT NULL DEFAULT 'active',
  
  FOREIGN KEY (custid) REFERENCES customers (custid)
) ENGINE=InnoDB;

/* insert data into transaction */
INSERT INTO `transaction` (`custid`,`paymenttype`,`price`,`cardholder`,`cardname`,`cardno`,`expirydate`) 
VALUES (1,'credit','2100','Preet Acharya','VISA','4502178124023652','2019/02');
INSERT INTO `transaction` (`custid`,`paymenttype`,`price`,`cardholder`,`cardname`,`cardno`,`expirydate`) 
VALUES (2,'credit','600','Ujjawal Tivari','MASTER','4502178124523652','2020/02');
