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
-- 비밀번호 암호화 처리 위해 크기 변경
ALTER TABLE MEMBER MODIFY USERPWD VARCHAR2(100);

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
        
        
select * from member;

desc member;



        