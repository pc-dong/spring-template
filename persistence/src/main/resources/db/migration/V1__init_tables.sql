CREATE TABLE account
(
    id          VARCHAR(64) not null primary key,
    name VARCHAR(128),
    created_at TIMESTAMP,
    last_updated_at TIMESTAMP,
    version BIGINT
);