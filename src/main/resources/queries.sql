############# Create table and Inserts ############

CREATE TABLE `attendance` (
	`attendanceID` int not null auto_increment,
  `date_value` varchar(50) DEFAULT NULL,
  `task` varchar(500) DEFAULT NULL,
  `inTime` varchar(50) DEFAULT NULL,
  `outTime` varchar(50) DEFAULT NULL,
  `duration` varchar(45) as (timediff(outTime,inTime)),
  primary key(attendanceID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into dbname.attendance2(date_value,task,inTime,outTime) values('2017-03-17','meet schools','12:25','19:30');

drop table dbname.feedback;

CREATE TABLE `feedback` (
  `feedbackID` int not null auto_increment,
  `date_value` varchar(30) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `customerID` varchar(10) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  primary key(feedbackID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from feedback;

alter table dbname.feedback
  add column shopName varchar(40),
  add column address varchar(50),
  add column phoneNumber varchar(20);

insert into dbname.feedback(date_value, customerName, customerID,description)
values('2017-03-17','laxmi fancy','c1','feed1');

insert into dbname.feedback(date_value, customerName, customerID,description,shopName,address,phoneNumber)
values('2017-03-17','mamatha','c2','feed1','lax fancy','308 108th ave','847824542');

