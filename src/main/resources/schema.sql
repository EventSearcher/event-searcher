--USERS
create table IF NOT EXISTS USERS(
    id serial primary key,
    username VARCHAR(255) not null,
    password VARCHAR(255) not null);
--ROLE
create table If NOT EXISTS ROLE(
    id integer primary key,
    name VARCHAR(255) not null);
--USERS_ROLE
create table IF NOT EXISTS USERS_ROLE(
    user_id integer references USERS(id),
    role_id integer references ROLE(id),
    CONSTRAINT users_roles_pk primary key(user_id,role_id));

