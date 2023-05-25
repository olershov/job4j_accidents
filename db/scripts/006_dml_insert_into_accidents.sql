INSERT INTO accidents (name, text, address, type_id)
VALUES ('Превышение скорости', 'Превышение на 20 км/ч', 'Волгоградский проспект 3',
(select id from accident_types where name = 'Машина - нарушение'));

INSERT INTO accidents (name, text, address, type_id)
VALUES ('Нарушение', 'Не пропустил пешехода на переходе', 'Пролетарская 23',
(select id from accident_types where name = 'Машина и человек'));

INSERT INTO accidents (name, text, address, type_id)
VALUES ('Дтп', 'Выезд на встречную полосу, Столкновение со встречным авто', 'Малая Бронная',
(select id from accident_types where name = 'Две машины'));
