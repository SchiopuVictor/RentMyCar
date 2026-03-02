create table invoices
(
    id             bigint auto_increment
        primary key,
    booking_id     bigint    not null,
    invoice_number bigint    not null,
    amount         int       not null,
    issued_at      timestamp not null,
    constraint invoices_bookings_id_fk
        foreign key (booking_id) references bookings (id)
);

