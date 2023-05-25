CREATE TABLE if not exists accidents (
  id serial primary key,
  name varchar,
  text varchar,
  address varchar,
  type_id int REFERENCES accident_types(id)
);

comment on table accidents is 'Таблица инцидентов';
comment on column accidents.id is 'Идентификатор инцидента';
comment on column accidents.name is 'Имя инцидента';
comment on column accidents.text is 'Описание инцидента';
comment on column accidents.address is 'Адрес инцидента';
comment on column accidents.type_id is 'Идентификатор типа инцидента';
