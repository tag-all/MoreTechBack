create sequence customer_seq start with 1 increment by 1;

create table customer
(
    id                  int                   NOT NULL
        CONSTRAINT pk_customer primary key,
    name                varchar(125),
    last_name           varchar(125),
    email               varchar(125) unique   not null,
    password            text                  not null,
    lvl                 int     default 0     not null,
    xp                  int     default 0     not null,
    balance             int     default 0     not null,
    notification_status boolean default false not null
);

create sequence token_seq start with 1 increment by 1;

create table token
(
    id      int  not null
        constraint pk_token primary key,
    user_id int references customer (id),
    token   text not null
);

create sequence activity_seq;

create table activity
(
    id               int         not null
        constraint pk_activities primary key,
    user_id          int references customer (id),
    type             varchar(64) not null,
    activity_link_id int,
    activity_date    timestamp   not null
);

create sequence file_seq;

create table file
(
    id          uuid      not null
        constraint pk_file primary key,
    name        text      not null
        expansion varchar (10) not null,
    data        text      not null,
    create_date timestamp not null
);

create sequence achievement_seq;

create table achievement
(
    id        int not null
        constraint pk_achievement primary key,
    file_uuid uuid references file (id),
    name varchar (64) not null
);

create sequence achievement_attribute_seq;

create table achievement_attribute
(
    id        int not null
        constraint pk_achievement_attribute primary key,
    achievement_id int references achievement (id),
    type  varchar(32) not null,
    value text        not null
);

create table achievement_user
(
    user_id        int references customer (id),
    achievement_id int references achievement (id),
    achievement_date timestamp not null,
    primary key (user_id, achievement_id)
);


create sequence product_seq;

create table product
(
    id        int not null
        constraint pk_product primary key,
    file_uuid uuid references file (id),
    name varchar (64) not null,
    price int not null,
    purchased int not null  default 0,
    number_of_products int not null default 0,
    type varchar(64) not null
);
