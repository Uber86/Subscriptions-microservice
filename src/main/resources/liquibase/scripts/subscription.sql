-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    email varchar(50) UNIQUE NOT NULL,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    birthday varchar(10) NOT NULL,
    phone varchar(12) NOT NULL,
    info varchar(300)
);

-- changeset oss:2
CREATE TABLE subscriptions(
    id BIGSERIAL PRIMARY KEY,
    service_name varchar(30),
    data_start TIMESTAMP,
    data_end TIMESTAMP,
    user_id BIGINT REFERENCES users(id)
);