#############Testing for FullCalendar##############

#Employees(7 employees)
insert into employees values ('Alec','Gralewski','840 9 Ave SW, Calgary, AB T2P 0L9','587-766-8877','alecgralewski@gmail.com','C',1,'Need more training!');
insert into employees values ('Anthony','Doucet','800 6 Ave SW, Calgary, AB T2P 3G3','403-577-966','anthonydoucet@gmail.com','C',1,'Need more training!');
insert into employees values ('Jason','Sy','1415 14 Ave NW, Calgary, AB T2N 1M4','587-777-333','jasonsy@gmail.com','C',1,'Need more training!');
insert into employees values ('Matthew','Kelemen','512 18 St NW, Calgary, AB T2N 2G5','403-123-456','matthewkelemen@gmail.com','S',1,'Need more training!');
insert into employees values ('Qiqi','Wu','2404 Kensington Rd NW, Calgary, AB T2N 3S1','587-966-3731','qiqiwu@gmail.com','S',1,'Need more training!');
insert into employees values ('John','Wood','1818 16 Ave NW, Calgary, AB T2M 0L8','403-855-123','johnwood@gmail.com','S',1,'Need more training!');
insert into employees values ('Kitty','Chen','1401 20 Ave NW, Calgary, AB T2M 1G6','587-567-890','kittychen@gmail.com','S',1,'Need more training!');

#Days format('YYYY-MM-DD HH:MM:SS')
insert into days values (null,'2019-03-11 12:00:00','2019-03-11 23:00:00');
insert into days values (null,'2019-03-12 12:00:00','2019-03-12 23:00:00');
insert into days values (null,'2019-03-13 12:00:00','2019-03-13 23:00:00');
insert into days values (null,'2019-03-14 12:00:00','2019-03-14 23:00:00');
insert into days values (null,'2019-03-15 12:00:00','2019-03-16 02:00:00');
insert into days values (null,'2019-03-16 12:00:00','2019-03-16 23:00:00');
insert into days values (null,'2019-03-17 12:00:00','2019-03-17 23:00:00');

#Shift(2 cooks, 2 servers; but for Friday, there are 2 cooks and 3 servers)
insert into shift values (null,1,1,'2019-03-11 12:00:00','2019-03-11 17:00:00','D');
insert into shift values (null,1,2,'2019-03-11 17:00:00','2019-03-11 23:00:00','N');
insert into shift values (null,1,4,'2019-03-11 12:00:00','2019-03-11 17:00:00','D');
insert into shift values (null,1,5,'2019-03-11 17:00:00','2019-03-11 23:00:00','N');

insert into shift values (null,2,1,'2019-03-12 12:00:00','2019-03-12 17:00:00','D');
insert into shift values (null,2,3,'2019-03-12 17:00:00','2019-03-12 23:00:00','N');
insert into shift values (null,2,5,'2019-03-12 12:00:00','2019-03-12 17:00:00','D');
insert into shift values (null,2,6,'2019-03-12 17:00:00','2019-03-12 23:00:00','N');

insert into shift values (null,3,2,'2019-03-13 12:00:00','2019-03-13 17:00:00','D');
insert into shift values (null,3,3,'2019-03-13 17:00:00','2019-03-13 23:00:00','N');
insert into shift values (null,3,4,'2019-03-13 12:00:00','2019-03-13 17:00:00','D');
insert into shift values (null,3,7,'2019-03-13 17:00:00','2019-03-13 23:00:00','N');

insert into shift values (null,4,1,'2019-03-14 12:00:00','2019-03-14 17:00:00','D');
insert into shift values (null,4,3,'2019-03-14 17:00:00','2019-03-14 23:00:00','N');
insert into shift values (null,4,6,'2019-03-14 12:00:00','2019-03-14 17:00:00','D');
insert into shift values (null,4,7,'2019-03-14 17:00:00','2019-03-14 23:00:00','N');

insert into shift values (null,5,2,'2019-03-15 12:00:00','2019-03-15 17:00:00','D');
insert into shift values (null,5,3,'2019-03-15 17:00:00','2019-03-15 23:00:00','N');
insert into shift values (null,5,4,'2019-03-15 12:00:00','2019-03-15 17:00:00','D');
insert into shift values (null,5,5,'2019-03-15 16:00:00','2019-03-15 22:00:00','N');
insert into shift values (null,5,6,'2019-03-15 18:00:00','2019-03-16 02:00:00','N');

insert into shift values (null,6,1,'2019-03-16 12:00:00','2019-03-16 17:00:00','D');
insert into shift values (null,6,2,'2019-03-16 17:00:00','2019-03-16 23:00:00','N');
insert into shift values (null,6,4,'2019-03-16 12:00:00','2019-03-16 17:00:00','D');
insert into shift values (null,6,7,'2019-03-16 17:00:00','2019-03-16 23:00:00','N');

insert into shift values (null,7,2,'2019-03-17 12:00:00','2019-03-17 17:00:00','D');
insert into shift values (null,7,3,'2019-03-17 17:00:00','2019-03-17 23:00:00','N');
insert into shift values (null,7,5,'2019-03-17 12:00:00','2019-03-17 17:00:00','D');
insert into shift values (null,7,7,'2019-03-17 17:00:00','2019-03-17 23:00:00','N');