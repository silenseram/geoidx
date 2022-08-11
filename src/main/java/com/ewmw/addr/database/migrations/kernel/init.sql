create table migration
(
    id      serial
        constraint migration_pk
        primary key,
    name    varchar(512) not null,
    applied boolean      not null
);

create unique index migration_id_uindex
    on migration (id);
