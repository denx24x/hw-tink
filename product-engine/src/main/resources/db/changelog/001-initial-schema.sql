CREATE TABLE product (
    code VARCHAR NOT NULL,
    version VARCHAR NOT NULL,
    loan_term_min INTEGER NOT NULL,
    loan_term_max INTEGER NOT NULL,
    principal_amount_min NUMERIC NOT NULL,
    principal_amount_max NUMERIC NOT NULL,
    interest_min NUMERIC NOT NULL,
    interest_max NUMERIC NOT NULL,
    origination_amount_min NUMERIC NOT NULL,
    origination_amount_max NUMERIC NOT NULL,
    PRIMARY KEY (code, version)
);

INSERT INTO product (
    code, version,
    loan_term_min, loan_term_max,
    principal_amount_min, principal_amount_max,
    interest_min, interest_max,
    origination_amount_min, origination_amount_max)
VALUES (
    'CL', '1.0',
    3, 24,
    50000, 500000,
    8, 15,
    2000, 10000
);

CREATE TYPE AGREEMENT_STATUS AS ENUM ('NEW', 'ACTIVE', 'CLOSED');

CREATE TABLE agreement (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    product_code VARCHAR NOT NULL,
    product_version VARCHAR NOT NULL,
    loan_term INTEGER NOT NULL,
    principal_amount NUMERIC NOT NULL,
    interest NUMERIC NOT NULL,
    origination_amount NUMERIC NOT NULL,
    status AGREEMENT_STATUS NOT NULL,
    disbursement_date DATE,
    next_payment_date DATE,
    FOREIGN KEY (product_code, product_version) REFERENCES Product (code, version)
);

CREATE TYPE PAYMENT_STATUS AS ENUM ('PAID', 'OVERDUE', 'FUTURE');

CREATE TABLE payment_schedule (
    id SERIAL PRIMARY KEY,
    agreement_id INTEGER NOT NULL,
    FOREIGN KEY (agreement_id) REFERENCES agreement (id)
);

CREATE TABLE payment_schedule_payment (
    id SERIAL PRIMARY KEY,
    schedule_id INTEGER NOT NULL,
    payment_date DATE NOT NULL,
    period_payment NUMERIC NOT NULL,
    interest_payment NUMERIC NOT NULL,
    principal_payment NUMERIC NOT NULL,
    period_number INTEGER NOT NULL,
    status PAYMENT_STATUS NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES payment_schedule (id)
)
