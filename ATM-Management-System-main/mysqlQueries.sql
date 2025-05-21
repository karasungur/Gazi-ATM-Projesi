create database ATM;

show databases;
use atm;

create table signup(formno varchar(50), name varchar(20), father_name varchar(20), dob varchar(20),
gender varchar(20), email varchar(40), marital varchar(20), address varchar(40), city varchar(20), 
state varchar(20), pincode varchar(20));


-- show tables;


create table signuptwo(formno varchar(50), segment varchar(50), experience varchar(50), income varchar(50), qualification varchar(50), occupation varchar(50), vkn_number varchar(50), tc_number varchar(50), 
senior_citizen varchar(50), existing_account varchar(50));

create table signupthree(formno varchar(50), account_type varchar(30), card_number varchar(30), pin_number varchar(20), facilities varchar(100));

create table login(formno varchar(20), cardNumber varchar(30), PinNumber varchar(10));


create table bank(pin varchar(10), date varchar(100), type varchar(30), amount varchar(50));

-- drop table bank;
