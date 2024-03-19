
/* Drop Tables */
/*
DROP TABLE BBS CASCADE CONSTRAINTS;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
*/



/* Create Tables */

CREATE TABLE BBS
(
	NO number NOT NULL,
	ID varchar2(20) NOT NULL,
	TITLE varchar2(50) NOT NULL,
	CONTENT varchar2(100) NOT NULL,
	HITCOUNT number DEFAULT 0,
	POSTDATE date DEFAULT SYSDATE,
	PRIMARY KEY (NO)
);


CREATE TABLE MEMBER
(
	ID varchar2(20) NOT NULL,
	PWD varchar2(20) NOT NULL,
	NAME varchar2(10) NOT NULL,
	GENDER varchar2(1) DEFAULT 'M',
	INTERS varchar2(500),
	EDUCATION varchar2(50),
	SELFINTRODUCE varchar2(1000),
	REGIDATE date DEFAULT SYSDATE,
	PRIMARY KEY (ID)
);



/* Create Foreign Keys */

ALTER TABLE BBS
	ADD FOREIGN KEY (ID)
	REFERENCES MEMBER (ID)
;

create sequence seq_bbs
 nocache
 nocycle;

