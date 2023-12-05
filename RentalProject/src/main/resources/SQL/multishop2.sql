DROP INDEX PK_upCategory;

/* 상위카테고리 */
DROP TABLE upCategory 
	CASCADE CONSTRAINTS;

/* 상위카테고리 */
CREATE TABLE upCategory (
	upcg_code NUMBER(8) NOT NULL, /* 상위카테고리 코드 */
	upcg_name VARCHAR2(30) NOT NULL /* 상위카테고리명 */
);

CREATE UNIQUE INDEX PK_upCategory
	ON upCategory (
		upcg_code ASC
	);

ALTER TABLE upCategory
	ADD
		CONSTRAINT PK_upCategory
		PRIMARY KEY (
			upcg_code
		);
---------------------------------------------------------
DROP INDEX PK_downCategory;

/* 하위 카테고리 */
DROP TABLE downCategory 
	CASCADE CONSTRAINTS;

/* 하위 카테고리 */
CREATE TABLE downCategory (
	upcg_code NUMBER(8) NOT NULL, /* 상위카테고리 코드 */
	downcg_code NUMBER(8) NOT NULL, /* 하위 카테고리 코드 */
	downcg_name VARCHAR2(30) NOT NULL /* 하위카테고리명 */
);

CREATE UNIQUE INDEX PK_downCategory
	ON downCategory (
		upcg_code ASC,
		downcg_code ASC
	);

ALTER TABLE downCategory
	ADD
		CONSTRAINT PK_downCategory
		PRIMARY KEY (
			upcg_code,
			downcg_code
		);

ALTER TABLE downCategory
	ADD
		CONSTRAINT FK_upCategory_TO_downCategory
		FOREIGN KEY (
			upcg_code
		)
		REFERENCES upCategory (
			upcg_code
		);

--------------------------------------------------------------
DROP INDEX PK_Products;

/* 상품 */
DROP TABLE Products 
	CASCADE CONSTRAINTS;

/* 상품 */
CREATE TABLE Products (
	pnum NUMBER(8) NOT NULL, /* 상품번호 */
	pname Varchar2(50) NOT NULL, /* 상품명 */
	pimage1 VARCHAR2(100), /* 이미지1 */
	pimage2 VARCHAR2(100), /* 이미지2 */
	pimage3 VARCHAR2(100), /* 이미지3 */
	price NUMBER(8) NOT NULL, /* 상품 정가 */
	saleprice NUMBER(8), /* 상품 판매가 */
	pqty NUMBER(8), /* 상품 수량 */
	point NUMBER(8), /* 포인트 */
	pspec VARCHAR2(20), /* 스펙 */
	pcontent VARCHAR2(1000), /* 상품설명 */
	pcompany VARCHAR2(50), /* 제조사 */
	pindate DATE, /* 등록일 */
	upcg_code NUMBER(8), /* 상위카테고리 코드 */
	downcg_code NUMBER(8) /* 하위 카테고리 코드 */
);

CREATE UNIQUE INDEX PK_Products
	ON Products (
		pnum ASC
	);

ALTER TABLE Products
	ADD
		CONSTRAINT PK_Products
		PRIMARY KEY (
			pnum
		);

ALTER TABLE Products
	ADD
		CONSTRAINT FK_downCategory_TO_Products
		FOREIGN KEY (
			upcg_code,
			downcg_code
		)
		REFERENCES downCategory (
			upcg_code,
			downcg_code
		);

ALTER TABLE Products
	ADD
		CONSTRAINT FK_upCategory_TO_Products
		FOREIGN KEY (
			upcg_code
		)
		REFERENCES upCategory (
			upcg_code
		);

-------------------------------------------------------------
DROP INDEX PK_Cart;

/* 장바구니 */
DROP TABLE Cart 
	CASCADE CONSTRAINTS;

/* 장바구니 */
CREATE TABLE Cart (
	cartNum NUMBER(8) NOT NULL, /* 장바구니번호 */
	userid_fk VARCHAR2(20) NOT NULL, /* 아이디 */
	pnum_fk NUMBER(8) NOT NULL, /* 상품번호 */
	pqty NUMBER(8), /* 수량 */
	indate DATE /* 등록일 */
);


CREATE UNIQUE INDEX PK_Cart
	ON Cart (
		cartNum ASC
	);

ALTER TABLE Cart
	ADD
		CONSTRAINT PK_Cart
		PRIMARY KEY (
			cartNum
		);

ALTER TABLE Cart
	ADD
		CONSTRAINT FK_Member_TO_Cart
		FOREIGN KEY (
			userid_fk
		)
		REFERENCES Member (
			userid
		) on delete cascade ;

ALTER TABLE Cart
	ADD
		CONSTRAINT FK_Products_TO_Cart
		FOREIGN KEY (
			pnum_fk
		)
		REFERENCES Products (
			pnum
		)  on delete cascade  ;
        
ALTER TABLE Cart
    ADD
        CONSTRAINT CK_PRODUCTS_PQTY
        CHECK (PQTY>0 AND PQTY <51);
--------------------------------------------------------------

DROP SEQUENCE upCategory_seq;
create sequence upCategory_seq nocache;


drop sequence downCategory_seq;
create sequence downCategory_seq nocache;


drop sequence products_seq;
create sequence products_seq nocache;

drop sequence cart_seq;
create sequence cart_seq nocache;


insert into upCategory values(upCategory_seq.nextval,'전자제품');
insert into upCategory values(upCategory_seq.nextval,'생활용품');
insert into upCategory values(upCategory_seq.nextval,'의류');
commit;
select * from upCategory;

insert into downCategory(upCg_code, downCg_code, downCg_name)
values(1,downCategory_seq.nextval,'가전제품');
insert into downCategory(upCg_code, downCg_code, downCg_name)
values(1,downCategory_seq.nextval,'컴퓨터');
commit;

insert into downCategory(upCg_code, downCg_code, downCg_name)
values(2,downCategory_seq.nextval,'주방세제');
insert into downCategory(upCg_code, downCg_code, downCg_name)
values(2,downCategory_seq.nextval,'휴지');
commit;

insert into downCategory(upCg_code, downCg_code, downCg_name)
values(3,downCategory_seq.nextval,'여성의류');
insert into downCategory(upCg_code, downCg_code, downCg_name)
values(3,downCategory_seq.nextval,'남성의류');
commit;

select * from downCategory;





