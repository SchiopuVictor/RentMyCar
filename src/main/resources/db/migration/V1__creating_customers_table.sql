create table customers
(
    id                    bigint auto_increment
        primary key,
    first_name            varchar(255) not null,
    last_name             varchar(255) not null,
    email                 varchar(255) not null,
    phone                 varchar(50)  not null,
    driver_license_number varchar(50)  not null,
    created_at            timestamp    not null
);

