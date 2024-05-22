CREATE TYPE TRANSACTION_STATUS AS ENUM ('NEW', 'PROCESSING', 'SUCCESS', 'ERROR');
CREATE CAST (varchar AS TRANSACTION_STATUS) WITH INOUT AS IMPLICIT;
create sequence s_transaction_application;
create table transactions_application
(
    id                 bigint                            default nextval('s_transaction_application'),
    application_id     SERIAL                   not null,
    status             VARCHAR                  not null,
    created_at         timestamp with time zone not null default clock_timestamp(),
    updated_at         timestamp with time zone not null default clock_timestamp(),
    transaction_status TRANSACTION_STATUS       not null default 'NEW'
);