create table cars
(
    id            bigint auto_increment
        primary key,
    brand         varchar(75)   not null,
    model         varchar(75)   not null,
    year          bigint        not null,
    license_plate varchar(50)   not null,
    price_per_day decimal(7, 2) not null,
    status        varchar(100)  not null,
    created_at    timestamp     not null
);

