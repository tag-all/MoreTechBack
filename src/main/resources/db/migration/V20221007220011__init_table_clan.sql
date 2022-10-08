create sequence clan_seq;

create table clan
(
    id        int  not null
        constraint pk_clan primary key,
    name      text not null,
    start_img text not null
);

create table clan_user
(
    user_id  int references customer (id),
    clan_id int references clan (id),
    unique (user_id, clan_id)
);