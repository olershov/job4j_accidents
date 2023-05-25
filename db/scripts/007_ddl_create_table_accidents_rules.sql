CREATE TABLE if not exists accidents_rules (
  id serial primary key,
  accident_id int REFERENCES accidents(id),
  rule_id int REFERENCES rules(id)
);

comment on table accidents_rules is 'Таблица для связи many-to-many инцидентов и статей';
comment on column accidents_rules.accident_id is 'Идентификатор инцидента';
comment on column accidents_rules.rule_id is 'Идентификатор статьи';