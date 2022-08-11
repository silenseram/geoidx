create table "user_limit"
(
    id serial
        constraint limits_pk
        primary key,
    user_id         integer
        references "user"(id),
    limit_id        integer null,
    queries_left    integer default 0
);