--USERS
create table IF NOT EXISTS USERS(
    id serial primary key,
    username VARCHAR(255) not null,
    password VARCHAR(255),not null,
    role int,
    foreign key (role) references USERS_ROLE(role_id));
--ROLE
create table If NOT EXISTS ROLE(
    id int primary key,
    name VARCHAR(255) not null);
--USERS_ROLE
create table IF NOT EXISTS USERS_ROLE(
    user_id int not null,
    role_id int not null,
    foreign key (role_id) references ROLE(id),
    foreign key (user_id) references USERS(id));

INSERT INTO role VALUES (1,'USER');
INSERT INTO role VALUES (2,'ADMIN');
