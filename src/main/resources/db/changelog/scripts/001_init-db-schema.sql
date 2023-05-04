-- liquibase formatted sql
-- changeset artemii.aleksenko:001_init-db-schema.sql
CREATE TYPE role_type AS ENUM ('USER', 'ADMIN');
-- rollback DROP TYPE matrix_status;

CREATE TABLE "user"
(
    "id"         UUID PRIMARY KEY,
    "first_name" VARCHAR(100) NOT NULL,
    "last_name"  VARCHAR(100) NOT NULL,
    "email"      VARCHAR(255) NOT NULL,
    "password"   VARCHAR(255) NOT NULL,
    "role"       role_type    NOT NULL
);
-- rollback DROP TABLE user;
