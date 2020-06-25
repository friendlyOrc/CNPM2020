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
    rentalFlatId int,
	foreign key(rentalFlatId) references tblrentalflat(id)
);

create table tblRentedRoom(
	id int primary key auto_increment,
    rentingPrice float,
    numberofperson int,
    roomid int,
    contractid int
);

create table tblBill(
	id int primary key auto_increment,
    `month` int,
    rentingFee float,
    serviceFee float,
    waternumber float,
    electricitynumber float,
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
    numberofroomrented int,
    contractduration int,
    userid int,
    clientid int
);

create table tblStaticService(
	id int primary key auto_increment,
    `name` varchar(255),
    `description` varchar(255) 
);

create table tblRentedRoomStaticService(
	id int primary key auto_increment,
    price float,
    `number` int,
    staticserviceid int,
    rentedroomid int
);

create table tblMonthlyService(
	id int primary key auto_increment,
    `name` varchar(255),
    `description` varchar(255) 
);

create table tblRentedRoomMonthlyService(
	id int primary key auto_increment,
    price float,
    `number` int,
    monthlyserviceid int,
    rentedroomid int
);

create table tblUser(
	id int primary key auto_increment,
    `name` varchar(255),
    address varchar(255),
    dateofbirth date,
    identitynumber varchar(255),
    phonenumber varchar(255),
    `account` varchar(255),
    `password` varchar(255)
);
ALTER TABLE tblRentedRoom
ADD FOREIGN KEY (roomid) REFERENCES tblroom(id);
ALTER TABLE tblRentedRoom
ADD FOREIGN KEY (contractid) REFERENCES tblcontract(id);
ALTER TABLE tblBill
ADD FOREIGN KEY (contractid) REFERENCES tblcontract(id);
ALTER TABLE tblContract
ADD FOREIGN KEY (userid) REFERENCES tbluser(id);
ALTER TABLE tblContract
ADD FOREIGN KEY (clientid) REFERENCES tblclient(id);
ALTER TABLE tblRentedRoomStaticService
ADD FOREIGN KEY (staticserviceid) REFERENCES tblstaticservice(id);
ALTER TABLE tblRentedRoomStaticService
ADD FOREIGN KEY (rentedroomid) REFERENCES tblrentedroom(id);
ALTER TABLE tblRentedRoomMonthlyService
ADD FOREIGN KEY (monthlyserviceid) REFERENCES tblmonthlyservice(id);
ALTER TABLE tblRentedRoomMonthlyService
ADD FOREIGN KEY (rentedroomid) REFERENCES tblrentedroom(id);

-- INSERT-- 
insert into tblrentalflat(address, numberofroom, numberofperson, `description`) values('Nam Từ Liêm, Hà Nội', 5, 4, 'Nhà mặt đường');

insert into tblroom(price, floor, `type`, rentalflatid) values(1500000, 1, 'Single', 1);
insert into tblroom(price, floor, `type`, rentalflatid) values(2000000, 2, 'Double', 1);
insert into tblroom(price, floor, `type`, rentalflatid) values(1500000, 3, 'Single', 1);

insert into tblUser(`name`, address, dateofbirth, identitynumber, phonenumber, `account`, `password`) values('Phạm Kiên', 'Nam Từ Liêm, Hà Nội', 20/07/1999, '0123456789', '0987654321', 'admin', 'admin123');

insert into tblClient(`name`, address, dateofbirth, identitynumber, phonenumber) 
values('Nguyễn Thắng', 'Thái Bình', 19990128, '0127594736', '098163547'),
('Hoàng Khải', 'Hà Nội', 19990404, '0765434562', '092237682'),
('Hoàng Tâm', 'Hà Nội', 19991012, '1234785902', '096917476');

insert into tblStaticService(`name`, `description`) 
values('Điện', 'Tiền điện 1 tháng'),
('Nước', 'Tiền nước tính theo khối');

insert into tblMonthlyService(`name`, `description`) 
values('Gửi xe', 'Tiền gửi xe tính theo số xe'),
('Vệ sinh', 'Tiền dọn vệ sinh hàng tháng');

insert into tblContract(checkin, numberOfRoomRented, contractDuration, userid, clientid)
values(20200501, 1, 6, 1, 1),
(20200401, 1, 6, 1, 2),
(20200601, 1, 6, 1, 3);

select * from tblcontract;

insert into tblRentedRoom(rentingprice, numberofPerson, roomid, contractid) values
(1500000, 1, 1, 1),
(2000000, 2, 2, 2),
(1500000, 1, 3, 3);

insert into tblRentedRoomMonthlyService(price, `number`, monthlyserviceid, rentedroomid) values
(2300, 2000, 1, 1),
(10000, 50, 2, 1),
(2300, 2000, 1, 2),
(10000, 50, 2, 2),
(2300, 2000, 1, 3),
(10000, 50, 2, 3);

insert into tblRentedRoomStaticService(price, `number`, staticserviceid, rentedroomid) values
(50000, 1, 1, 1),
(10000, 1, 2, 1),
(50000, 2, 1, 2),
(10000, 1, 2, 2),
(50000, 1, 1, 3),
(10000, 0, 2, 3);


insert into tblBill(`month`, rentingFee, serviceFee, waterNumber, electricityNumber, billStatus, contractid) values
(4, 2000000, 110000, 5, 80, 1, 2),
(5, 1500000, 60000, 7, 120, 1, 1),
(5, 2000000, 100000, 3, 185, 1, 2),
(6, 1500000, 60000, 2, 140, 1, 1),
(6, 2000000, 110000, 7, 200, 1, 2),
(6, 1500000, 50000, 8, 95, 1, 3);
