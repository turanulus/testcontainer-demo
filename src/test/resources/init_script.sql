CREATE TABLE app_user
(
    id     bigserial NOT NULL PRIMARY KEY,
    name   text      NOT NULL,
    status integer   NOT NULL
);

insert into app_user (name, status)
values ('Turan', 1);

insert into app_user (name, status)
values ('Ulus', 1);