--liquibase formatted sql

--changeset valentinkd3:1
create extension if not exists pgcrypto;

--changeset valentinkd3:2
update users set password = crypt(password, gen_salt('bf'));