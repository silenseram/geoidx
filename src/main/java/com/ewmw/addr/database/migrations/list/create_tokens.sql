create table token
(
    id serial
        constraint tokens_pk
        primary key,
    value       varchar(512),
    user_id     bigint       null,
    "limit"     bigint,
    spent       bigint default 0,
    created_at  timestamp,

    constraint token_id_uidx
        unique (id)
);

create index token_user_id_idx
    on token (user_id);

create index token_value_idx
    on token (value);