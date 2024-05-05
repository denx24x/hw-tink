CREATE TABLE overdue_balance
(
    id           SERIAL PRIMARY KEY,
    balance      NUMERIC NOT NULL,
    agreement_id INTEGER NOT NULL,
    FOREIGN KEY (agreement_id) REFERENCES agreement (id)
)
