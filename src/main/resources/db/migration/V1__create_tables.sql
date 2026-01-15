CREATE TABLE clients (
    id VARCHAR(36) NOT NULL,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    second_last_name VARCHAR(255),
    dni VARCHAR(255),
    api_token VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE bank_accounts (
    id BIGINT AUTO_INCREMENT NOT NULL,
    iban VARCHAR(255),
    balance DECIMAL(19, 2),
    client_id VARCHAR(36),
    PRIMARY KEY (id),
    CONSTRAINT fk_bank_accounts_client FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE credit_cards (
    id BIGINT AUTO_INCREMENT NOT NULL,
    number VARCHAR(255),
    expiration_date DATE,
    cvv INT,
    name VARCHAR(255),
    bank_account_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_credit_cards_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_accounts (id)
);

CREATE TABLE bank_movements (
    id BIGINT AUTO_INCREMENT NOT NULL,
    movement_type VARCHAR(50),
    movement_origin VARCHAR(50),
    timestamp DATETIME(6),
    amount DECIMAL(19, 2),
    concept VARCHAR(255),
    bank_account_id BIGINT,
    origin_credit_card_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_bank_movements_bank_account FOREIGN KEY (bank_account_id) REFERENCES bank_accounts (id),
    CONSTRAINT fk_bank_movements_credit_card FOREIGN KEY (origin_credit_card_id) REFERENCES credit_cards (id)
);

create table sesions(
    id char(36) primary key,
    token_value varchar(255) not null unique,
    client_id char(36) not null,
    created_at datetime not null
);