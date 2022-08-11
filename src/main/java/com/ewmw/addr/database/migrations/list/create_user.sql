create table "user"
(
    id serial
        constraint users_pk
        primary key,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    email      varchar(255) null,
    password   text         null,

    constraint email_uidx
        unique (email)
);