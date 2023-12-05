drop table memo;

create table memo(
    idx number(8), --글번호
    name varchar2(30) not null, --작성자
    msg varchar2(200), --메모내용
    wdate date default sysdate,--작성일
    constraint memo_pk primary key (idx)
);

drop sequence memo_seq;

create sequence memo_seq nocache;
