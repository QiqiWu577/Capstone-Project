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
                         emp_id int(4) AUTO_INCREMENT primary key,
                         fname varchar(40) not null,
                         lname varchar(40) not null,
                         address varchar(50) not null,
                         phone_no varchar(15) not null,
                         email varchar(60) not null,
                         type char(1) not null,
                         newHire bit not null,
                         active bit not null,
                         notes varchar(1000)
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
                             notif_id INT(5) AUTO_INCREMENT,
                             sender INT(4) NOT NULL,
                             recipient INT(4) NOT NULL,
                             content VARCHAR(1000),
                             date DATETIME NOT NULL,
                             notif_type CHAR(1) NOT NULL,
                             Status CHAR(1) NOT NULL,
                             PRIMARY KEY(notif_id),
                             FOREIGN KEY (sender) references Employees(emp_id)
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
                            not_the_same_day bit,
                            PRIMARY KEY(day_of_week)
);

CREATE TABLE shift_template (
                              shift_id    int(6) auto_increment,
                              name        VARCHAR(10),
                              type        char(1),
                              day_of_week VARCHAR(10),
                              start_time  VARCHAR(8),
                              end_time    VARCHAR(8),
                              min_no_emp  INT(2),
                              max_no_emp  INT(2),
                              PRIMARY KEY(shift_id),
                              FOREIGN KEY (day_of_week) REFERENCES day_template (day_of_week)
);






