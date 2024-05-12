create sequence s_transaction_agreement;
CREATE TYPE TRANSACTION_STATUS AS ENUM ('NEW', 'PROCESSING', 'SUCCESS', 'ERROR');
create table transactions_agreement
(
    id                 bigint                            default nextval('s_transaction_agreement'),
    agreement_id       SERIAL                   not null,
    status             AGREEMENT_STATUS         not null,
    disbursement_date  DATE,
    next_payment_date  DATE,
    created_at         timestamp with time zone not null default clock_timestamp(),
    updated_at         timestamp with time zone not null default clock_timestamp(),
    transaction_status TRANSACTION_STATUS       not null default 'NEW'
);