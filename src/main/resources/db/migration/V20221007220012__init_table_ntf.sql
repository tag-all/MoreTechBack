create sequence nft_seq;

create table nft
(
    id           int  not null
        constraint pk_nft primary key,
    user_id      int references customer (id),
    clan_id      int references clan (id),
    tx_hash      text,
    name         text,
    price        int
);