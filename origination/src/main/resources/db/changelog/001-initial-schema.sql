CREATE TABLE client (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    salary VARCHAR NOT NULL
);

CREATE TABLE application (
    id SERIAL PRIMARY KEY,
    client_id INTEGER NOT NULL,
    requested_disbursement_amount NUMERIC NOT NULL,
    status VARCHAR NOT NULL,
    creation_time DATE NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client (id)
);