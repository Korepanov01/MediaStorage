-- Исходные данные "public.file_type"
INSERT INTO "public.file_type" ("name")
VALUES
    ('THUMBNAIL'),
    ('MAIN');

-- Исходные данные "public.role"
INSERT INTO "public.role" ("name")
VALUES
    ('ADMIN'),
    ('USER');

-- Исходные данные "public.media_type"
INSERT INTO "public.media_type" ("name")
VALUES
    ('Изображение'),
    ('Видео'),
    ('Аудио');

-- Тестовые данные "public.tag"
INSERT INTO "public.tag" ("name")
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

-- Тестовые данные "public.category"
INSERT INTO "public.category" ("name", "parent_category_id")
VALUES
    ('Природа', NULL),  --1
    ('Города', NULL),   --2
    ('Пейзажи', 1),     --3
    ('Животные', 1),    --4
    ('Архитектура', 2), --5
    ('Дома', 5),        --6
    ('Памятники', 5);   --7

-- Тестовые данные "public.user"
INSERT INTO "public.user" ("name", "password_hash", "email")
VALUES
    ('Иван', 'hashed_password_1', 'user1@example.com'),
    ('Сергей', 'hashed_password_2', 'user2@example.com'),
    ('Александр', 'hashed_password_3', 'user3@example.com'),
    ('Анатолий', 'hashed_password_4', 'user4@example.com'),
    ('Игорь', 'hashed_password_5', 'user5@example.com');

-- Тестовые данные "public.media"
INSERT INTO "public.media" ("user_id", "category_id", "name", "description", "media_type_id", "created_at", "edited_at")
VALUES
    (1, 3, 'Запись 1', 'Описание записи 1', 1, NOW(), NOW()),
    (2, 4, 'Запись 2', NULL, 1, NOW(), NOW()),
    (3, 5, 'Запись 3', 'Описание записи 3', 2, NOW(), NOW()),
    (4, 6, 'Запись 4', NULL, 3, NOW(), NOW()),
    (5, 7, 'Запись 5', 'Описание записи 5', 1, NOW(), NOW()),
    (1, 3, 'Запись 6', NULL, 2, NOW(), NOW()),
    (2, 4, 'Запись 7', 'Описание записи 7', 3, NOW(), NOW()),
    (3, 5, 'Запись 8', NULL, 1, NOW(), NOW()),
    (4, 6, 'Запись 9', 'Описание записи 9', 2, NOW(), NOW()),
    (5, 7, 'Запись 10', NULL, 1, NOW(), NOW()),
    (1, 3, 'Запись 11', 'Описание записи 11', 3, NOW(), NOW()),
    (2, 4, 'Запись 12', NULL, 2, NOW(), NOW());

-- Тестовые данные "public.user_role"
INSERT INTO "public.user_role" ("role_id", "user_id")
VALUES
    (1, 1),
    (2, 2),
    (2, 3),
    (2, 4),
    (2, 5);

-- Тестовые данные "public.media_tag"
INSERT INTO "public.media_tag" ("media_id", "tag_id")
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