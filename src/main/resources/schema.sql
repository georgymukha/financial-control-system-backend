CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE wallets (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    owner_id UUID NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE accounts (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    wallet_id UUID NOT NULL,
    owner_id UUID NOT NULL,
    balance DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    amount DECIMAL(19, 2) NOT NULL,
    type VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    wallet_id UUID NOT NULL,
    account_id UUID NOT NULL,
    user_id UUID NOT NULL,
    date TIMESTAMP NOT NULL,
    description TEXT,
    FOREIGN KEY (wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (account_id) REFERENCES accounts(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE transaction_items (
    id UUID PRIMARY KEY,
    transaction_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    quantity DECIMAL(19, 2) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (transaction_id) REFERENCES transactions(id)
);

CREATE TABLE categories (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    is_custom BOOLEAN NOT NULL
);

CREATE TABLE access_rights (
    id UUID PRIMARY KEY,
    wallet_id UUID NOT NULL,
    user_id UUID NOT NULL,
    access_level VARCHAR(255) NOT NULL,
    FOREIGN KEY (wallet_id) REFERENCES wallets(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);