DROP INDEX PK_Member;

/* 회원 */
DROP TABLE Member 
	CASCADE CONSTRAINTS;

/* 회원 */
CREATE TABLE Member (
	userid VARCHAR2(20) NOT NULL, /* 아이디 */
	name VARCHAR2(30) NOT NULL, /* 이름 */
	userpwd VARCHAR2(100) NOT NULL, /* 비밀번호 */
	hp1 CHAR(3) NOT NULL, /* 연락처1 */
	hp2 CHAR(4) NOT NULL, /* 연락처2 */
	hp3 CHAR(4) NOT NULL, /* 연락처3 */
	post CHAR(5), /* 우편번호 */
	addr1 VARCHAR2(200), /* 주소1 */
	addr2 VARCHAR2(200), /* 주소2 */
	indate DATE, /* 가입일 */
	mileage NUMBER(8), /* 적립금 */
	mstate NUMBER(1) /* 회원상태 */
);

COMMENT ON TABLE Member IS '회원';

COMMENT ON COLUMN Member.userid IS '아이디';

COMMENT ON COLUMN Member.name IS '이름';

COMMENT ON COLUMN Member.userpwd IS '비밀번호';

COMMENT ON COLUMN Member.hp1 IS '연락처1';

COMMENT ON COLUMN Member.hp2 IS '연락처2';

COMMENT ON COLUMN Member.hp3 IS '연락처3';

COMMENT ON COLUMN Member.post IS '우편번호';

COMMENT ON COLUMN Member.addr1 IS '주소1';

COMMENT ON COLUMN Member.addr2 IS '주소2';

COMMENT ON COLUMN Member.indate IS '가입일';

COMMENT ON COLUMN Member.mileage IS '적립금';

COMMENT ON COLUMN Member.mstate IS '회원상태';

CREATE UNIQUE INDEX PK_Member
	ON Member (
		userid ASC
	);

ALTER TABLE Member
	ADD
		CONSTRAINT PK_Member
		PRIMARY KEY (
			userid
		);
        
desc member;

alter table member modify userpwd varchar2(100);