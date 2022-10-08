create sequence transfer_seq;

create table transfer
(
    id           int  not null
        constraint pk_transfer primary key,
    user_send    int references customer (id),
    user_get     int references customer (id),
    transfer_key text not null
);