CREATE TABLE balance
(
    id           VARCHAR PRIMARY KEY,
    balance      NUMERIC NOT NULL,
    agreement_id INTEGER NOT NULL,
    FOREIGN KEY (agreement_id) REFERENCES agreement (id)
)
