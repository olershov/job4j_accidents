CREATE TABLE if not exists users (
 id serial primary key,
 username varchar(50) NOT NULL unique,
 password varchar(100) NOT NULL,
 enabled boolean default true,
 authority_id int not null references authorities(id)
);

comment on table users is 'Таблица пользователей';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.username is 'Имя пользователя';
comment on column users.password is 'Пароль пользователя';
comment on column users.enabled is 'Статус активности';
comment on column users.authority_id is 'Идентификатор роли';