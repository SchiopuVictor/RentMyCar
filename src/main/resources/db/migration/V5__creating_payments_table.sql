create table payments
(
    id             bigint auto_increment
        primary key,
    invoice_id     bigint         not null,
    amount         decimal(10, 2) not null,
    payment_method varchar(100)   not null,
    status         varchar(50)    not null,
    paid_at        timestamp      not null,
    constraint payments_invoices_id_fk
        foreign key (invoice_id) references invoices (id)
);

