-- Исходные данные "file_type"
INSERT INTO "file_type" ("name")
VALUES
    ('Превью'),
    ('Основной');

-- Исходные данные "role"
INSERT INTO "role" ("name")
VALUES
    ('ADMIN'),
    ('SUPER_ADMIN');

-- Исходные данные "media_type"
INSERT INTO "media_type" ("name")
VALUES
    ('Изображение'),
    ('Видео'),
    ('Аудио');

-- Тестовые данные "tag"
INSERT INTO "tag" ("name")
VALUES
    ('Природа'),
    ('Города'),
    ('Путешествия'),
    ('Еда'),
    ('Искусство'),
    ('Музыка'),
    ('Фотография'),
    ('Культура'),
    ('Наука'),
    ('Технологии'),
    ('Спорт'),
    ('Здоровье'),
    ('Рисунки');

-- Тестовые данные "category"
INSERT INTO "category" ("name", "parent_category_id")
VALUES
    ('Природа', NULL),  --1
    ('Города', NULL),   --2
    ('Пейзажи', 1),     --3
    ('Животные', 1),    --4
    ('Архитектура', 2), --5
    ('Дома', 5),        --6
    ('Памятники', 5);   --7

-- Тестовые данные "user"
INSERT INTO "user" ("name", "password_hash", "email")
VALUES
    ('Иван', '$2a$10$gpcA36S9xR.EQN79LhLBFOKypg61z9mhQsGaPaiqQiCW8sWtJilma', 'string'),
    ('Сергей', '$2a$10$gpcA36S9xR.EQN79LhLBFOKypg61z9mhQsGaPaiqQiCW8sWtJilma', 'string1'),
    ('Александр', '$2a$10$gpcA36S9xR.EQN79LhLBFOKypg61z9mhQsGaPaiqQiCW8sWtJilma', 'string2'),
    ('Анатолий', '$2a$10$gpcA36S9xR.EQN79LhLBFOKypg61z9mhQsGaPaiqQiCW8sWtJilma', 'string3'),
    ('Игорь', '$2a$10$gpcA36S9xR.EQN79LhLBFOKypg61z9mhQsGaPaiqQiCW8sWtJilma', 'string4');

-- Тестовые данные "media"
INSERT INTO "media" ("user_id", "category_id", "name", "description", "media_type_id")
VALUES
    (1, 3, 'Запись 1', 'Описание записи 1', 1),
    (2, 4, 'Запись 2', NULL, 1),
    (3, 5, 'Запись 3', 'Описание записи 3', 2),
    (4, 6, 'Запись 4', NULL, 1),
    (5, 7, 'Запись 5', 'Описание записи 5', 1),
    (1, 3, 'Запись 6', NULL, 1),
    (2, 4, 'Запись 7', 'Описание записи 7', 1),
    (3, 5, 'Запись 8', NULL, 1),
    (4, 6, 'Запись 9', 'Описание записи 9', 1),
    (5, 7, 'Запись 10', NULL, 1),
    (1, 3, 'Запись 11', 'Описание записи 11', 1),
    (2, 4, 'Запись 12', NULL, 1);

-- Тестовые данные "user_role"
INSERT INTO "user_role" ("role_id", "user_id")
VALUES
    (1, 1),
    (2, 1),
    (1, 2);

-- Тестовые данные "media_tag"
INSERT INTO "media_tag" ("media_id", "tag_id")
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10),
    (11, 11),
    (12, 12);