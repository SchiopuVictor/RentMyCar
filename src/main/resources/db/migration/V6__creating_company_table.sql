create table Company
(
    id      bigint auto_increment
        primary key,
    address varchar(255) not null,
    tell    varchar(255) not null,
    email   varchar(255) not null,
    cui     varchar(255) not null
);

