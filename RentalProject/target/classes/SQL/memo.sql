create table memo(
    idx number(8),
    name varchar2(30) not null,
    msg VARCHAR2(200),
    wdate date default sysdate,
    constraint memo_pk primary key (idx)
);

create sequence memo_seq nocache;