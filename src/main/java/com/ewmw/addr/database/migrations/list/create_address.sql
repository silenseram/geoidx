create table address
(
    id serial
        constraint address_pk
        primary key,
    addr            varchar(512)    null,
    OBJECTID        bigint          null,
    path            varchar(256)    null,
    uuid            uuid            null,
    level           int             null,
    constraint address_id_uindex
        unique (id)
);

create index address_OBJECTID_index
    on address (OBJECTID);

create unique index address_id_uindex
    on address (id);