create table gar_house
(
    id         bigint      not null
        primary key,
    OBJECTID   bigint      null,
    OBJECTGUID varchar(37) null,
    HOUSENUM   varchar(128)null,
    ADDNUM1    varchar(128)null,
    ADDNUM2    varchar(128)null,
    HOUSETYPE  int         null,
    ADDTYPE1   varchar(128)null,
    ADDTYPE2   varchar(128)null,
    OKATO      varchar(128)null,
    OKTMO      varchar(128)null,
    POSTALCODE int         null,
    ISACTUAL   int         null,
    ISACTIVE   int         null,
    constraint gar_house_id_uindex
        unique (id)
);

create index gar_house_OBJECTID_index
    on gar_house (OBJECTID);