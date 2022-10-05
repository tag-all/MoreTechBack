create sequence customer_seq;

create table customer
(
    id       int primary key,
    name     varchar(125),
    last_name varchar(125),
    age      int,
    email    varchar(125) unique not null,
    password text                not null
);

create sequence token_seq;

create table token
(
    id      int primary key,
    user_id int references customer (id),
    token   text not null
);