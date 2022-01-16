INSERT INTO tz_roles (id, name, authority) VALUES
(1, 'Обычный пользователь', 'ROLE_USER'), (2, 'Инспектор', 'ROLE_ADMIN');

INSERT INTO tz_accident_stats (id, name) VALUES
(1, 'Новый'), (2, 'В обработке'), (3, 'Отклонен'), (4, 'Завершен');

INSERT INTO tz_accident_types (name) VALUES
('Две машины'), ('Машина и пешеход'), ('Машина и велосипедист');

INSERT INTO tz_rules (name) VALUES
('Статья №1'), ('Статья №2'), ('Статья №3');

INSERT INTO tz_users (id_role, name, email, pass, phone) VALUES
(2, 'Власов Александр Сергеевич', 'velesov7493@gmail.com', '$2a$10$ZpIdL7fznzRTwqY.uOjNvePEokUXFLBCqXIM6Rac7p.CNBlyMYfn2', '+79621671681'),
(1, 'Иванов Иван Иванович', 'ivan7493@gmail.com', '$2a$10$TenvxzZG8AOdAbt60SAdve3tsh07JxKEJ5DWMSLfKqKxeoKZ6TF0G', '+79621671679');

INSERT INTO tj_accidents (id_type, id_state, id_author, name, actor1number, actor2number, description, address) VALUES
(1, 1, 2, 'ДТП-1', 'м963мм/37', 'т969ку/37', 'Столкновение Lexus MC 300 и ВАЗ-2107', 'г. Иваново, перекресток ул. Лежневской и ул. 10 Августа'),
(1, 2, 2, 'ДТП-2', 'т710хя/37', 'м667ку/37', 'Столкновение Ford Focus и и Ferrari 360 Spider', 'г. Иваново, перекресток ул. Лежневской и пр. Строителей'),
(2, 4, 2, 'ДТП-3', 'т710хя/37', null, 'Ford Focus сбит пешеход на переходе', 'г. Иваново, перекресток ул. Ташкентской и пр. Строителей');

INSERT INTO tr_accidents_rules (id_accident, id_rule) VALUES
(1, 1),
(2, 2),
(3, 1), (3, 3);