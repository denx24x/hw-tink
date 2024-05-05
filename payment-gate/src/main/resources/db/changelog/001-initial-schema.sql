CREATE TABLE disbursement_transfer
(
    id           SERIAL PRIMARY KEY,
    transfer_id  INTEGER NOT NULL,
    agreement_id INTEGER NOT NULL,
    status       VARCHAR NOT NULL,
    finish_date  TIMESTAMP
);

CREATE TABLE payment_transfer
(
    id          SERIAL PRIMARY KEY,
    transfer_id INTEGER NOT NULL,
    status      VARCHAR NOT NULL,
    amount      NUMERIC NOT NULL,
    balance_id  VARCHAR NOT NULL,
    finish_date TIMESTAMP
);