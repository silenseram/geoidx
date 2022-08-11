create table gar_path
(
    id serial
        constraint table_name_pk
        primary key,
    OBJECTID   bigint       null,
    is_active  varchar(2)   null,
    path       varchar(512) null,

    constraint gar_path_id_uindex
        unique (id)
);

create index gar_path_OBJECTID_index
    on gar_path (OBJECTID)