--ɾ������
drop sequence bsm_admin_id;
drop sequence bsm_user_id;
drop sequence bsm_category_id;
drop sequence bsm_book_id;
drop sequence bsm_lend_id;
--��������
create sequence bsm_admin_id;
create sequence bsm_user_id;
create sequence bsm_category_id;
create sequence bsm_book_id;
create sequence bsm_lend_id;
--ɾ����
drop table bsm_admin;
drop table bsm_user;
drop table bsm_book;
drop table bsm_category;
drop table bsm_lend;
--������
--����Ա��
create table bsm_admin(
	id number(7) primary key,
	name VARCHAR2(56) UNIQUE,
	password VARCHAR2(56),
	createdate DATE,
	lastlogin DATE,
	realname VARCHAR2(56),
	phone VARCHAR2(128),
	flag NUMBER(3),
	status NUMBER(3)
);
--�û���
create table bsm_user(
	id number(7) primary key,
	no VARCHAR2(128) UNIQUE,
	createdate DATE,
	realname VARCHAR2(56),
	phone VARCHAR2(128),
	status NUMBER(3),
	admin_id NUMBER(7)
);
--����
create table bsm_category(
	id NUMBER(7) primary key,
	name VARCHAR2(56),
	location VARCHAR2(255),
	admin_id NUMBER(7)
);
--ͼ���
create table bsm_book(
	id number(7) primary key,
	isbn VARCHAR2(128),
	name VARCHAR2(256),
	author VARCHAR2(128),
	publishhouse VARCHAR2(128),
	count NUMBER(7),
	restcount NUMBER(7),
	ondate DATE,
	bookstatus NUMBER(3),
	category_id NUMBER(7),
	admin_id NUMBER(7)
);
--�����
create table bsm_lend(
	id number(7) primary key,
	lenddate DATE,
	returndate DATE,
	count NUMBER(7),
	lendstatus NUMBER(3),
	user_id NUMBER(7),
	book_id NUMBER(7),
	admin_id NUMBER(7)
);



