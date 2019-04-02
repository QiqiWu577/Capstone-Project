#############Testing for FullCalendar##############

#day_template
insert into day_template values ('Monday','00:00:00','23:59:59');
insert into day_template values ('Tuesday','00:00:00','23:59:59');
insert into day_template values ('Wednesday','00:00:00','23:59:59');
insert into day_template values ('Thursday','00:00:00','23:59:59');
insert into day_template values ('Friday','00:00:00','23:59:59');
insert into day_template values ('Saturday','00:00:00','23:59:59');
insert into day_template values ('Sunday','00:00:00','23:59:59');

#shift_template
insert into shift_template values (null,'S','Open', 'Monday','12:00:00','20:00:00',1,2);
insert into shift_template values (null,'S','Mid', 'Monday','13:00:00','21:00:00',1,2);
insert into shift_template values (null,'S','Close', 'Monday','14:00:00','22:00:00',1,2);
insert into shift_template values (null,'K','Open', 'Monday','12:00:00','20:00:00',1,2);
insert into shift_template values (null,'K','Mid', 'Monday','13:00:00','21:00:00',1,2);
insert into shift_template values (null,'K','Close', 'Monday','14:00:00','22:00:00',1,2);
insert into shift_template values (null,'B','Open', 'Monday','12:00:00','20:00:00',1,2);
insert into shift_template values (null,'B','Mid', 'Monday','13:00:00','21:00:00',1,2);
insert into shift_template values (null,'B','Close', 'Monday','14:00:00','22:00:00',1,2);

#Employee(7 employees)
insert into employees values (null,'Alec','Gralewski','840 9 Ave SW, Calgary, AB T2P 0L9','587-766-8877','alecgralewski@gmail.com','K',1,1,'Need more training!');
insert into employees values (null,'Anthony','Doucet','800 6 Ave SW, Calgary, AB T2P 3G3','403-577-966','anthonydoucet@gmail.com','K',1,1,'Need more training!');
insert into employees values (null,'Jason','Sy','1415 14 Ave NW, Calgary, AB T2N 1M4','587-777-333','jasonsy@gmail.com','K',1,1,'Need more training!');
insert into employees values (null,'Matthew','Kelemen','512 18 St NW, Calgary, AB T2N 2G5','403-123-456','matthewkelemen@gmail.com','B',1,1,'Need more training!');
insert into employees values (null,'Qiqi','Wu','2404 Kensington Rd NW, Calgary, AB T2N 3S1','587-966-3731','qiqiwu@gmail.com','B',1,1,'Need more training!');
insert into employees values (null,'John','Wood','1818 16 Ave NW, Calgary, AB T2M 0L8','403-855-123','johnwood@gmail.com','S',1,1,'Need more training!');
insert into employees values (null,'Kitty','Chen','1401 20 Ave NW, Calgary, AB T2M 1G6','587-567-890','kittychen@gmail.com','S',1,1,'Need more training!');


#EmployeeConstraints
insert into employee_constraints values (1, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (2, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (3, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (4, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (5, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (6, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (7, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');


#Days format('YYYY-MM-DD HH:MM:SS')
insert into days values (null,'2019-03-11 12:00:00','2019-03-11 23:00:00');
insert into days values (null,'2019-03-12 12:00:00','2019-03-12 23:00:00');
insert into days values (null,'2019-03-13 12:00:00','2019-03-13 23:00:00');
insert into days values (null,'2019-03-14 12:00:00','2019-03-14 23:00:00');
insert into days values (null,'2019-03-15 12:00:00','2019-03-16 02:00:00');
insert into days values (null,'2019-03-16 12:00:00','2019-03-16 23:00:00');
insert into days values (null,'2019-03-17 12:00:00','2019-03-17 23:00:00');

#Shift(2 cooks, 2 servers; but for Friday, there are 2 cooks and 3 servers)
insert into shift values (null,1,'2019-03-11 12:00:00','2019-03-11 17:00:00','D');
insert into shift values (null,1,'2019-03-11 17:00:00','2019-03-11 23:00:00','N');

insert into shift values (null,2,'2019-03-12 12:00:00','2019-03-12 17:00:00','D');
insert into shift values (null,2,'2019-03-12 17:00:00','2019-03-12 23:00:00','N');

insert into shift values (null,3,'2019-03-13 12:00:00','2019-03-13 17:00:00','D');
insert into shift values (null,3,'2019-03-13 17:00:00','2019-03-13 23:00:00','N');

insert into shift values (null,4,'2019-03-14 12:00:00','2019-03-14 17:00:00','D');
insert into shift values (null,4,'2019-03-14 17:00:00','2019-03-14 23:00:00','N');

insert into shift values (null,5,'2019-03-15 12:00:00','2019-03-15 17:00:00','D');
insert into shift values (null,5,'2019-03-15 17:00:00','2019-03-15 23:00:00','N');
insert into shift values (null,5,'2019-03-15 13:00:00','2019-03-15 16:00:00','M');

insert into shift values (null,6,'2019-03-16 12:00:00','2019-03-16 17:00:00','D');
insert into shift values (null,6,'2019-03-16 17:00:00','2019-03-16 23:00:00','N');

insert into shift values (null,7,'2019-03-17 12:00:00','2019-03-17 17:00:00','D');
insert into shift values (null,7,'2019-03-17 17:00:00','2019-03-17 23:00:00','N');

#schedule_employee
insert into schedule_employee values (1,1);
insert into schedule_employee values (2,2);
insert into schedule_employee values (4,1);
insert into schedule_employee values (5,2);
insert into schedule_employee values (7,1);

insert into schedule_employee values (1,3);
insert into schedule_employee values (3,4);
insert into schedule_employee values (5,3);
insert into schedule_employee values (6,4);
insert into schedule_employee values (7,4);

insert into schedule_employee values (2,5);
insert into schedule_employee values (3,6);
insert into schedule_employee values (4,5);
insert into schedule_employee values (7,6);

insert into schedule_employee values (1,7);
insert into schedule_employee values (3,8);
insert into schedule_employee values (6,7);
insert into schedule_employee values (7,8);

insert into schedule_employee values (2,9);
insert into schedule_employee values (3,10);
insert into schedule_employee values (1,9);
insert into schedule_employee values (4,9);
insert into schedule_employee values (5,10);
insert into schedule_employee values (6,9);

insert into schedule_employee values (1,12);
insert into schedule_employee values (2,13);
insert into schedule_employee values (4,12);
insert into schedule_employee values (7,13);

insert into schedule_employee values (2,14);
insert into schedule_employee values (3,15);
insert into schedule_employee values (5,14);
insert into schedule_employee values (7,15);


DELIMITER //
CREATE PROCEDURE get_login(IN empid int)
BEGIN
   SELECT hash, salt from salt where emp_id = empid;
END //
DELIMITER ;


insert into employees values (6666, "Admin", "Admin", "123 Admin", "123-456-7890", "admin@admin.com", 'A', 0, 1, "This is a temporary admin account");

insert into salt values (6666, "fbbd9d8e1575ddf9915c2f14f456ecd60848e69621ae1a956540164e49716e4932fb16b18fa0fc990a88cb778861a677de777e34dbeeacdf87fd7efafd1500d1", "taWgQ7LFW+DqiXhE5R195g==");

insert into employees values (7777, "Manager", "Manager", "123 Manager", "123-456-7890", "manager@manager.com", 'M', 0, 1, "This is a temporary manager account");

insert into salt values (7777, "b86b8cf5c720849c1f2158e5eaea8ee93d4959439b44d3bf52dc59b9f300896955d45d2d71f60dcba53c5c4a3b5bb702bff6457bc9c0c66bfb836f711c380dd7", "8t9fhkubdDfrbmQo9CnnJg==");

insert into employees values (8888, "Employee", "Employee", "123 Edmployee", "123-456-7890", "employee@employee.com", 'S', 0, 1, "This is a temporary employee account");

insert into salt values (8888, "e6953dd39f3537ca2331130af04550442746895747e6c2405cac1af7f3001b1a7e25ba93f56f532122eb979f246f0d57841ca0189a67602f6852291e09234eda", "TkWVVu0T42sJnw0RkBIDVw==");

insert into employee_constraints values (8888, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into employee_constraints values (7777, '000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,000000000000000000000000,');
insert into schedule_employee values (8888,1);
insert into schedule_employee values (8888,2);
insert into schedule_employee values (8888,3);

insert into employees values (9,'Mr','Manager','Addy','Number','Man@gmail.com','M',1,1,'Im The BOSS');

#Notification type
#A - all
#P - personal
#S - shift available
#D - department
#Status
#A - approved
#W - waiting
#D - declined
#N - normal
#I - important
insert into notifications values (null, 1,2,'random message',curdate(),'A','N',null);
insert into notifications values (null, 1,2,'random message',curdate(),'P','N',null);
insert into notifications values (null, 1,2,'random message',curdate(),'S','W',null);
insert into notifications values (null, 1,2,'random message',curdate(),'S','A',null);
insert into notifications values (null, 1,2,'random message',curdate(),'S','D',null);
insert into notifications values (null, 1,2,'random message',curdate(),'D','N',null);
insert into notifications values (null, 1,2,'random message',curdate(),'A','I',null);
insert into notifications values (null, 1,2,'Test Shift 1',curdate(),'S','W',null);
insert into notifications values (null, 1,2,'Test Shift 2',curdate(),'S','W',null);
insert into notifications values (null, 1,2,'Test Shift 3',curdate(),'S','W',null);
insert into notifications values (null, 1,2,'Test Shift 4',curdate(),'S','W',null);
insert into notifications values (null, 9,9,'random message 1',curdate(),'P','N',null);
insert into notifications values (null, 9,9,'random message 2',curdate(),'P','N',null);
insert into notifications values (null, 9,9,'random message 3',curdate(),'P','N',null);
insert into notifications values (null, 9,9,'random message 4',curdate(),'P','N',null);
insert into notifications values (null, 9,9,'random message 5',curdate(),'P','N',null);
insert into notifications values (null, 9,9,'random message',curdate(),'S','W',null);
