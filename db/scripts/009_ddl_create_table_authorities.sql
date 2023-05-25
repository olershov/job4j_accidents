CREATE TABLE if not exists authorities (
  id serial primary key,
  authority varchar(50) NOT NULL unique
);

comment on table authorities is 'Таблица ролей';
comment on column authorities.id is 'Идентификатор роли';
comment on column authorities.authority is 'Наименование роли';