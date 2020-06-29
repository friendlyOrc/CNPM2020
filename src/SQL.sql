drop database rentaldb;
create database rentaldb CHARACTER SET utf8 COLLATE utf8_unicode_ci;

use rentaldb;
create table tblrentalflat(
	id int primary key auto_increment,
    address varchar(255),
    numberofroom int,
    numberofperson int,
    `description` varchar(255)
);
create table tblRoom(
	id int primary key auto_increment,
    `name` varchar(255),
    price float,
    floor int,
    `type` varchar(255),
    `description` varchar(255),
    rentalFlatId int
);

create table tblBill(
	id int primary key auto_increment,
    `created` date,
    rentingFee float,
    serviceFee float,
    waternumber float,
    electricitynumber float,
    debt float,
    billstatus int,
    contractid int
);

create table tblClient(
	id int primary key auto_increment,
    `name` varchar(255),
    address varchar(255),
    dateofbirth date,
    identitynumber varchar(255),
    phonenumber varchar(255)
);

create table tblContract(
	id int primary key auto_increment,
    checkin date,
    rentingPrice float,
    deposit float,
    contractduration int,
    userid int,
    clientid int,
    roomid int
);

create table tblStaticService(
	id int primary key auto_increment,
    `name` varchar(255),
    `description` varchar(255) 
);

create table tblRoomStaticService(
	id int primary key auto_increment,
    price float,
    `number` int,
    staticserviceid int,
    roomid int
);

create table tblMonthlyService(
	id int primary key auto_increment,
    `name` varchar(255),
    `description` varchar(255) 
);

create table tblRoomMonthlyService(
	id int primary key auto_increment,
    price float,
    `number` int,
    monthlyserviceid int,
    roomid int
);

create table tblUser(
	id int primary key auto_increment,
    `name` varchar(255),
    address varchar(255),
    dateofbirth date,
    identitynumber varchar(255),
    phonenumber varchar(255),
    `username` varchar(255),
    `password` varchar(255)
);
ALTER TABLE tblRoom
ADD foreign key (rentalflatid) references tblRentalFlat(id);
ALTER TABLE tblBill
ADD FOREIGN KEY (contractid) REFERENCES tblcontract(id);
ALTER TABLE tblContract
ADD FOREIGN KEY (userid) REFERENCES tbluser(id);
ALTER TABLE tblContract
ADD FOREIGN KEY (clientid) REFERENCES tblclient(id);
ALTER TABLE tblContract
ADD FOREIGN KEY (roomid) REFERENCES tblRoom(id);
ALTER TABLE tblRoomStaticService
ADD FOREIGN KEY (staticserviceid) REFERENCES tblstaticservice(id);
ALTER TABLE tblRoomStaticService
ADD FOREIGN KEY (roomid) REFERENCES tblroom(id);
ALTER TABLE tblRoomMonthlyService
ADD FOREIGN KEY (monthlyserviceid) REFERENCES tblmonthlyservice(id);
ALTER TABLE tblRoomMonthlyService
ADD FOREIGN KEY (roomid) REFERENCES tblroom(id);

-- INSERT-- 
insert into tblrentalflat(address, numberofroom, numberofperson, `description`) values('Nam Từ Liêm, Hà Nội', 5, 4, 'Nhà mặt đường');

insert into tblroom(`name`, price, floor, `type`, rentalflatid) 
values('101', 1500000, 1, 'Single', 1),
('201', 12000000, 2, 'Double', 1),
('301', 11500000, 3, 'Single', 1);

insert into tblUser(`name`, address, dateofbirth, identitynumber, phonenumber, `username`, `password`) values('Phạm Kiên', 'Nam Từ Liêm, Hà Nội', 19990720, '0123456789', '0987654321', 'admin', 'admin123');

insert into tblClient(`name`, address, dateofbirth, identitynumber, phonenumber) 
values('Nguyễn Thắng', 'Thái Bình', 19990128, '0127594736', '098163547'),
('Hoàng Khải', 'Hà Nội', 19990404, '0765434562', '092237682'),
('Hoàng Tâm', 'Hà Nội', 19991012, '1234785902', '096917476');

insert into tblStaticService(`name`, `description`) 
values('Gửi xe', 'Tiền gửi xe tính theo số xe'),
('Vệ sinh', 'Tiền dọn vệ sinh hàng tháng');

insert into tblMonthlyService(`name`, `description`) 
values('Điện', 'Tiền điện 1 tháng'),
('Nước', 'Tiền nước tính theo khối');

insert into tblContract(checkin, rentingPrice, deposit, contractDuration, userid, clientid, roomid)
values(20191001, 1500000, 1500000, 6, 1, 1, 1),
(20200401, 2000000, 2000000, 6, 1, 2, 2),
(20200601, 1500000, 1500000, 6, 1, 3, 3);

select * from tblcontract;

insert into tblRoomMonthlyService(price, `number`, monthlyserviceid, roomid) values
(2300, 2000, 1, 1),
(10000, 50, 2, 1),
(2300, 2000, 1, 2),
(10000, 50, 2, 2),
(2300, 2000, 1, 3),
(10000, 50, 2, 3);

insert into tblRoomStaticService(price, `number`, staticserviceid, roomid) values
(50000, 1, 1, 1),
(10000, 1, 2, 1),
(50000, 2, 1, 2),
(10000, 1, 2, 2),
(50000, 1, 1, 3),
(10000, 0, 2, 3);


insert into tblBill(`created`, rentingFee, serviceFee, waterNumber, electricityNumber, debt, billStatus, contractid) values
(20191201, 2000000, 110000, 2, 80, 0, 1, 1),
(20200101, 2000000, 110000, 3, 90, 0, 1, 1),
(20200201, 2000000, 110000, 4, 70, 0, 1, 1),
(20200301, 2000000, 110000, 5, 90, 0, 1, 1),
(20200401, 2000000, 110000, 6, 100, 0, 1, 1),
(20200501, 1500000, 60000, 7, 120, 0, 1, 1),
(20200501, 2000000, 100000, 3, 185, 0, 1, 2),
(current_date(), 1500000, 60000, 2, 140, 0, 0, 1),
(20200601, 2000000, 110000, 7, 200, 0, 0, 2);

SELECT MONTH(tblBill.created) as `month`, 
		YEAR(tblBill.created) as `Year`, 
		sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep - tblBill.debt)) as income 
FROM tblBill, 
	tblContract, 
	(SELECT roomid, price as wp 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 2) as msw,
	(SELECT roomid, price as ep 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 1) as mep
 WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1
GROUP BY MONTH(tblBill.created);

SELECT YEAR(tblBill.created) as `year`, 
		sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep - tblBill.debt)) as income 
FROM tblBill, 
	tblContract, 
	(SELECT roomid, price as wp 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 2) as msw,
	(SELECT roomid, price as ep 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 1) as mep
 WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1
GROUP BY YEAR(tblBill.created);


SELECT QUARTER(tblBill.created) as `Quarter`, 
		YEAR(tblBill.created) as `Year`,  
        sum((tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep - tblBill.debt)) as income 
FROM tblBill, 
	tblContract, 
	(SELECT roomid, price as wp 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 2) as msw,
	(SELECT roomid, price as ep 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 1) as mep
 WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1
GROUP BY QUARTER(tblBill.created);

SELECT (tblBill.created) as `time`, tblBill.id as id, tblBill.contractid as contractid, 
		(tblBill.rentingFee + tblBill.serviceFee + tblBill.waterNumber*msw.wp + tblBill.electricityNumber*mep.ep - tblBill.debt) as income 
FROM tblBill, 
	tblContract, 
	(SELECT roomid, price as wp 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 2) as msw,
	(SELECT roomid, price as ep 
		FROM tblRoomMonthlyService
        WHERE monthlyserviceid = 1) as mep
 WHERE tblBill.contractid = tblContract.id AND mep.roomid = tblContract.roomid AND msw.roomid = tblContract.roomid  AND tblBill.billStatus = 1 AND MONTH(created) = 5 
 ORDER BY `time` DESC;
