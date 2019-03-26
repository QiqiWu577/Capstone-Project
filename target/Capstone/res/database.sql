drop database if exists schedulecapstone;

CREATE DATABASE ScheduleCapstone;

USE ScheduleCapstone;

drop table if exists shift_template;
drop table if exists day_template;
drop table if exists schedule_employee;
drop table if exists notifications;
drop table if exists shift;
drop table if exists days;
drop table if exists salt;
drop table if exists employee_constraints;
drop table if exists employees;

Create table employees (
                         Emp_id int(4) AUTO_INCREMENT primary key,
                         Fname varchar(40) not null,
                         lname varchar(40) not null,
                         Address varchar(50) not null,
                         Phone_no varchar(15) not null,
                         Email varchar(60) not null,
                         Type char(1) not null,
                         newHire bit not null,
                         Active bit not null,
                         Notes varchar(1000)
);

CREATE TABLE Employee_constraints (
                                    emp_id INT(4) AUTO_INCREMENT primary key,
                                    constraints VARCHAR(400) NOT NULL,
                                    FOREIGN KEY (emp_id) REFERENCES Employees(emp_id)
);

CREATE TABLE salt (
                    emp_id int(4),
                    hash VARCHAR(400),
                    salt VARCHAR(400),
                    PRIMARY KEY(emp_id),
                    FOREIGN KEY (emp_id) references Employees(emp_id)
);

CREATE TABLE Days (
                    day_id int(4) AUTO_INCREMENT,
                    start_time DATETIME not null,
                    end_time DATETIME not null,
                    primary key(day_id)
);

CREATE TABLE Shift(
                    shift_id int(6) auto_increment,
                    day_id int(4),
                    startTime DATETIME not null,
                    endTime DATETIME not null,
                    shift_type char(1) not null,
                    primary key(shift_id),
                    FOREIGN KEY (day_id) REFERENCES days(day_id)
);

CREATE TABLE Notifications (
                             Notif_id INT(5) AUTO_INCREMENT,
                             Sender INT(4) NOT NULL,
                             Recipient INT(4) NOT NULL,
                             Content VARCHAR(1000),
                             Date DATETIME NOT NULL,
                             Notif_type CHAR(1) NOT NULL,
                             Status CHAR(1) NOT NULL,
                             PRIMARY KEY(Notif_id),
                             FOREIGN KEY (Sender) references Employees(emp_id)
);

create table schedule_employee (
                             emp_id int(4),
                             shift_id int(6),
                             primary key(emp_id,shift_id),
                             FOREIGN KEY (emp_id) REFERENCES employees(emp_id),
                             FOREIGN KEY (shift_id) REFERENCES shift(shift_id)
);

CREATE TABLE day_template (
                            day_of_week VARCHAR(10),
                            open_time   VARCHAR(8),
                            close_time  VARCHAR(8),
                            PRIMARY KEY(day_of_week)
);

CREATE TABLE shift_template (
                              shift_id    int(6) auto_increment,
                              type        char(1),
                              name        VARCHAR(10),
                              day_of_week VARCHAR(10),
                              start_time  VARCHAR(8),
                              end_time    VARCHAR(8),
                              min_no_emp  INT(2),
                              max_no_emp  INT(2),
                              PRIMARY KEY(shift_id),
                              FOREIGN KEY (day_of_week) REFERENCES day_template (day_of_week)
);






