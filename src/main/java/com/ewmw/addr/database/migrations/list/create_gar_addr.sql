create table gar_addr
(
    id         bigint       not null
        primary key,
    level      int          not null,
    OBJECTGUID varchar(37)  null,
    NAME       varchar(512) null,
    TYPENAME   varchar(128) null,
    PREVID     varchar(128) null,
    NEXTID     varchar(128) null,
    CHANGEID   varchar(128) null,
    OPERTYPEID varchar(128) null,
    STARTDATE  varchar(128) null,
    ENDDATE    varchar(128) null,
    UPDATEDATE varchar(128) null,
    OBJECTID   bigint       null,
    OKTMO      int          null,
    OKATO      int          null,
    constraint gar_addr_id_uindex
        unique (id)
);

create index gar_addr_OBJECTID_index
    on gar_addr (OBJECTID);

