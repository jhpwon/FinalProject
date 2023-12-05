DROP INDEX PK_Member;

/* ȸ�� */
DROP TABLE Member 
	CASCADE CONSTRAINTS;

/* ȸ�� */
CREATE TABLE Member (
	userid VARCHAR2(20) NOT NULL, /* ���̵� */
	name VARCHAR2(30) NOT NULL, /* �̸� */
	userpwd VARCHAR2(100) NOT NULL, /* ��й�ȣ */
	hp1 CHAR(3) NOT NULL, /* ����ó1 */
	hp2 CHAR(4) NOT NULL, /* ����ó2 */
	hp3 CHAR(4) NOT NULL, /* ����ó3 */
	post CHAR(5), /* �����ȣ */
	addr1 VARCHAR2(200), /* �ּ�1 */
	addr2 VARCHAR2(200), /* �ּ�2 */
	indate DATE, /* ������ */
	mileage NUMBER(8), /* ������ */
	mstate NUMBER(1) /* ȸ������ */
);
-- ��й�ȣ ��ȣȭ ó�� ���� ũ�� ����
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



        