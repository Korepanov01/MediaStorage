DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA "public";


-- Таблица пользователей
CREATE TABLE "user"
(
    "id"                BIGSERIAL       CONSTRAINT "user_pk" PRIMARY KEY,
    "name"              VARCHAR(200)    NOT NULL,
    "password_hash"     VARCHAR(256)    NOT NULL,
    "email"             VARCHAR(500)    NOT NULL
);

CREATE UNIQUE INDEX uidx_user_name     ON "user" (LOWER("name")  varchar_pattern_ops);
CREATE UNIQUE INDEX uidx_user_email    ON "user" (LOWER("email") varchar_pattern_ops);


-- Таблица ролей
CREATE TABLE "role"
(
    "id"   BIGSERIAL    CONSTRAINT "role_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_role_name ON "role" (LOWER("name") varchar_pattern_ops);


-- Таблица связей пользователей и ролей
CREATE TABLE "user_role"
(
    "id"        BIGSERIAL   CONSTRAINT "user_role_pk" PRIMARY KEY,
    "role_id"   bigint      NOT NULL,
    "user_id"   bigint      NOT NULL
);

CREATE UNIQUE INDEX uidx_user_role_role_id_role_id  ON "user_role" ("role_id", "user_id");

ALTER TABLE "user_role"
    ADD CONSTRAINT "user_role_fk0"
        FOREIGN KEY ("role_id") REFERENCES "role" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "user_role"
    ADD CONSTRAINT "user_role_fk1"
        FOREIGN KEY ("user_id") REFERENCES "user" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица категорий
CREATE TABLE "category"
(
    "id"                 BIGSERIAL      CONSTRAINT "category_pk" PRIMARY KEY,
    "name"               VARCHAR(200)   NOT NULL,
    "parent_category_id" bigint
);

CREATE UNIQUE INDEX uidx_category_name          ON "category" (LOWER("name") varchar_pattern_ops);
CREATE INDEX idx_category_parent_category_id    ON "category" ("parent_category_id");

ALTER TABLE "category"
    ADD CONSTRAINT "category_fk0"
        FOREIGN KEY ("parent_category_id") REFERENCES "category" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица типов медиа
CREATE TABLE "media_type"
(
    "id"   BIGSERIAL    CONSTRAINT "media_type_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_media_type_name ON "media_type" (LOWER("name") varchar_pattern_ops);


-- Таблица медиа
CREATE TABLE "media"
(
    "id"            BIGSERIAL       CONSTRAINT "media_pk" PRIMARY KEY,
    "user_id"       bigint          NOT NULL,
    "category_id"   bigint          NOT NULL,
    "name"          VARCHAR(200)    NOT NULL,
    "description"   VARCHAR(10000),
    "media_type_id" bigint          NOT NULL
);

CREATE INDEX idx_media_name             ON "media" (LOWER("name") varchar_pattern_ops);
CREATE INDEX idx_media_media_type_id    ON "media" ("media_type_id");
CREATE INDEX idx_media_category_id      ON "media" ("category_id");

ALTER TABLE "media"
    ADD CONSTRAINT "media_fk0"
        FOREIGN KEY ("user_id") REFERENCES "user" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "media"
    ADD CONSTRAINT "media_fk1"
        FOREIGN KEY ("category_id") REFERENCES "category" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "media"
    ADD CONSTRAINT "media_fk2"
        FOREIGN KEY ("media_type_id") REFERENCES "media_type" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица тегов
CREATE TABLE "tag"
(
    "id"    BIGSERIAL       CONSTRAINT "tag_pk" PRIMARY KEY,
    "name"  VARCHAR(200)    NOT NULL
);

CREATE UNIQUE INDEX uidx_tag_name ON "tag"(LOWER("name") varchar_pattern_ops);


-- Таблица связей медиа и тегов
CREATE TABLE "media_tag"
(
    "id"       BIGSERIAL    CONSTRAINT "media_tag_pk" PRIMARY KEY,
    "media_id" bigint       NOT NULL,
    "tag_id"   bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_tag_media_id_tag_id ON "media_tag" ("media_id", "tag_id");

ALTER TABLE "media_tag"
    ADD CONSTRAINT "media_tag_fk0"
        FOREIGN KEY ("media_id") REFERENCES "media" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "media_tag"
    ADD CONSTRAINT "media_tag_fk1"
        FOREIGN KEY ("tag_id") REFERENCES "tag" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица типов файлов
CREATE TABLE "file_type"
(
    "id"   BIGSERIAL    CONSTRAINT "file_type_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_file_type_name ON "file_type" (LOWER("name") varchar_pattern_ops);


-- Таблица файлов
CREATE TABLE "file"
(
    "id"            BIGSERIAL       CONSTRAINT "file_pk" PRIMARY KEY,
    "name"          VARCHAR(200)    NOT NULL,
    "content_type"  VARCHAR(50)     NOT NULL,
    "size"          BIGINT          NOT NULL,
    "data"          BYTEA           NOT NULL,
    "file_type_id" bigint           NOT NULL
);

CREATE INDEX idx_file_file_type_id ON "file" ("file_type_id");

ALTER TABLE "file"
    ADD CONSTRAINT "file_file_type_fk2"
        FOREIGN KEY ("file_type_id") REFERENCES "file_type" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица связей файлов и медиа
CREATE TABLE "media_file"
(
    "id"           BIGSERIAL    CONSTRAINT "media_file_pk" PRIMARY KEY,
    "media_id"     bigint       NOT NULL,
    "file_id"      bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_file_media_id_file_id    ON "media_file" ("media_id", "file_id");
CREATE INDEX idx_media_file_media_id                    ON "media_file" ("media_id");

ALTER TABLE "media_file"
    ADD CONSTRAINT "media_file_fk0"
        FOREIGN KEY ("media_id") REFERENCES "media" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "media_file"
    ADD CONSTRAINT "media_file_fk1"
        FOREIGN KEY ("file_id") REFERENCES "file" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;