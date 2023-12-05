drop table spring_board;

--답변형 게시판

create table spring_board(
	num number(8) primary key, --글번호
	userid varchar2(20) not null references member (userid) on delete cascade, --작성자 아이디(FK)
	passwd varchar2(100) not null, --비번
	subject varchar2(300) not null,--제목
	content varchar2(4000), --글내용
	wdate date default sysdate, --작성일
	readnum number(8) default 0,
	filename varchar2(500), --첨부파일명[uuid_a.txt] ==> 물리적 파일명
	originFilename varchar2(500), --원본파일명 [a.txt] ==> 논리적 파일명
	filesize number(8),--첨부파일크기
	
	refer number(8), -- 글그룹번호 [답변형 게시판에서 필요한 컬럼]
	lev number(8),--답변 레벨  [답변형 게시판에서 필요한 컬럼]
	sunbun number(8)--같은 글 그룹내에 순서 정렬시 필요  [답변형 게시판에서 필요한 컬럼]
);

drop sequence spring_board_seq;

create sequence spring_board_seq nocache;