CREATE TABLE if not exists rules (
  id serial primary key,
  name varchar
);

comment on table rules is 'Таблица статей';
comment on column rules.id is 'Идентификатор статьи';
comment on column rules.name is 'Наименование статьи';

