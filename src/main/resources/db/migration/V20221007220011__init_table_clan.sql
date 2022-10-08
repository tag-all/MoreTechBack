create sequence transfer_seq;

create table clan
(
    id        int  not null
        constraint pk_transfer primary key,
    name      text not null,
    start_img text not null
);