create table bookings
(
    id            bigint auto_increment
        primary key,
    customer_id   bigint         not null,
    car_id        bigint         not null,
    start_date    timestamp      not null,
    end_date      timestamp      not null,
    total_days    int            not null,
    price_per_day decimal(10, 2) not null,
    total_price   decimal(10, 2) not null,
    status        varchar(255)   not null,
    created_at    timestamp      not null,
    constraint bookings_cars_id_fk
        foreign key (car_id) references cars (id),
    constraint bookings_customers_id_fk
        foreign key (customer_id) references customers (id)
);

