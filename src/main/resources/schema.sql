create table auth_users
(
    id         varchar primary key,
    full_name  varchar        not null,
    username   varchar unique not null,
    password   varchar        not null,
    email      varchar,
    phone      varchar,
    auth_role  varchar        not null,
    created_at timestamp default now(),
    updated_at timestamp default now(),
    created_by varchar,
    updated_by varchar,
    deleted    boolean   default false

);

create table ads
(
    id          varchar primary key,
    title       varchar not null,
    description text,
    category    varchar not null,
    currency    varchar check ( currency in ('UZS', 'USD')) default 'UZS',
    price       double precision check ( price >= 0 )       default 0,
    created_at  timestamp                                   default now(),
    updated_at  timestamp                                   default now(),
    created_by  varchar,
    updated_by  varchar,
    deleted     boolean                                     default false
);
create table images
(
    id            varchar primary key,
    file_name     varchar unique not null,
    original_name varchar        not null,
    size          bigint,
    content_type  varchar        not null,
    object_id     varchar
);


