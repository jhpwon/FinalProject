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

COMMENT ON TABLE Member IS 'ȸ��';

COMMENT ON COLUMN Member.userid IS '���̵�';

COMMENT ON COLUMN Member.name IS '�̸�';

COMMENT ON COLUMN Member.userpwd IS '��й�ȣ';

COMMENT ON COLUMN Member.hp1 IS '����ó1';

COMMENT ON COLUMN Member.hp2 IS '����ó2';

COMMENT ON COLUMN Member.hp3 IS '����ó3';

COMMENT ON COLUMN Member.post IS '�����ȣ';

COMMENT ON COLUMN Member.addr1 IS '�ּ�1';

COMMENT ON COLUMN Member.addr2 IS '�ּ�2';

COMMENT ON COLUMN Member.indate IS '������';

COMMENT ON COLUMN Member.mileage IS '������';

COMMENT ON COLUMN Member.mstate IS 'ȸ������';

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