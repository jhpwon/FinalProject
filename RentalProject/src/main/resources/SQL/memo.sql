drop table memo;

create table memo(
    idx number(8), --�۹�ȣ
    name varchar2(30) not null, --�ۼ���
    msg varchar2(200), --�޸𳻿�
    wdate date default sysdate,--�ۼ���
    constraint memo_pk primary key (idx)
);

drop sequence memo_seq;

create sequence memo_seq nocache;
