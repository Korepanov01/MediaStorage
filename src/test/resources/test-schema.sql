DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA "public";


-- Таблица пользователей
CREATE TABLE "public.user"
(
    "id"                BIGSERIAL       CONSTRAINT "user_pk" PRIMARY KEY,
    "name"              VARCHAR(200)    NOT NULL,
    "password_hash"     VARCHAR(256)    NOT NULL,
    "email"             VARCHAR(500)    NOT NULL
);

CREATE UNIQUE INDEX uidx_user_name     ON "public.user" (LOWER("name")  varchar_pattern_ops);
CREATE UNIQUE INDEX uidx_user_email    ON "public.user" (LOWER("email") varchar_pattern_ops);


-- Таблица ролей
CREATE TABLE "public.role"
(
    "id"   BIGSERIAL    CONSTRAINT "role_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_role_name ON "public.role" (LOWER("name") varchar_pattern_ops);


-- Таблица связей пользователей и ролей
CREATE TABLE "public.user_role"
(
    "id"        BIGSERIAL   CONSTRAINT "user_role_pk" PRIMARY KEY,
    "role_id"   bigint      NOT NULL,
    "user_id"   bigint      NOT NULL
);

CREATE UNIQUE INDEX uidx_user_role_role_id_role_id  ON "public.user_role" ("role_id", "user_id");

ALTER TABLE "public.user_role"
    ADD CONSTRAINT "user_role_fk0"
        FOREIGN KEY ("role_id") REFERENCES "public.role" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.user_role"
    ADD CONSTRAINT "user_role_fk1"
        FOREIGN KEY ("user_id") REFERENCES "public.user" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица категорий
CREATE TABLE "public.category"
(
    "id"                 BIGSERIAL      CONSTRAINT "category_pk" PRIMARY KEY,
    "name"               VARCHAR(200)   NOT NULL,
    "parent_category_id" bigint
);

CREATE UNIQUE INDEX uidx_category_name          ON "public.category" (LOWER("name") varchar_pattern_ops);
CREATE INDEX idx_category_parent_category_id    ON "public.category" ("parent_category_id");

ALTER TABLE "public.category"
    ADD CONSTRAINT "category_fk0"
        FOREIGN KEY ("parent_category_id") REFERENCES "public.category" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица типов медиа
CREATE TABLE "public.media_type"
(
    "id"   BIGSERIAL    CONSTRAINT "media_type_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_media_type_name ON "public.media_type" (LOWER("name") varchar_pattern_ops);


-- Таблица медиа
CREATE TABLE "public.media"
(
    "id"            BIGSERIAL       CONSTRAINT "media_pk" PRIMARY KEY,
    "user_id"       bigint          NOT NULL,
    "category_id"   bigint          NOT NULL,
    "name"          VARCHAR(200)    NOT NULL,
    "description"   VARCHAR(10000),
    "media_type_id" bigint          NOT NULL,
    "created_at"    TIMESTAMP       NOT NULL,
    "edited_at"     TIMESTAMP       NOT NULL
);

CREATE INDEX idx_media_name             ON "public.media" (LOWER("name") varchar_pattern_ops);
CREATE INDEX idx_media_media_type_id    ON "public.media" ("media_type_id");
CREATE INDEX idx_media_category_id      ON "public.media" ("category_id");
CREATE INDEX idx_media_created_at       ON "public.media" ("created_at");
CREATE INDEX idx_media_edited_at        ON "public.media" ("edited_at");

ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk0"
        FOREIGN KEY ("user_id") REFERENCES "public.user" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk1"
        FOREIGN KEY ("category_id") REFERENCES "public.category" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk2"
        FOREIGN KEY ("media_type_id") REFERENCES "public.media_type" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица тегов
CREATE TABLE "public.tag"
(
    "id"    BIGSERIAL       CONSTRAINT "tag_pk" PRIMARY KEY,
    "name"  VARCHAR(200)    NOT NULL
);

CREATE UNIQUE INDEX uidx_tag_name ON "public.tag"(LOWER("name") varchar_pattern_ops);


-- Таблица связей медиа и тегов
CREATE TABLE "public.media_tag"
(
    "id"       BIGSERIAL    CONSTRAINT "media_tag_pk" PRIMARY KEY,
    "media_id" bigint       NOT NULL,
    "tag_id"   bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_tag_media_id_tag_id ON "public.media_tag" ("media_id", "tag_id");

ALTER TABLE "public.media_tag"
    ADD CONSTRAINT "media_tag_fk0"
        FOREIGN KEY ("media_id") REFERENCES "public.media" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.media_tag"
    ADD CONSTRAINT "media_tag_fk1"
        FOREIGN KEY ("tag_id") REFERENCES "public.tag" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- Таблица файлов
CREATE TABLE "public.file"
(
    "id"            BIGSERIAL       CONSTRAINT "file_pk" PRIMARY KEY,
    "name"          VARCHAR(200)    NOT NULL,
    "content_type"  VARCHAR(50)     NOT NULL,
    "size"          BIGINT          NOT NULL,
    "data"          BYTEA           NOT NULL
);


-- Таблица типов файлов
CREATE TABLE "public.file_type"
(
    "id"   BIGSERIAL    CONSTRAINT "file_type_pk" PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_file_type_name ON "public.file_type" (LOWER("name") varchar_pattern_ops);


-- Таблица связей файлов и медиа
CREATE TABLE "public.media_file"
(
    "id"           BIGSERIAL    CONSTRAINT "media_file_pk" PRIMARY KEY,
    "media_id"     bigint       NOT NULL,
    "file_id"      bigint       NOT NULL,
    "file_type_id" bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_file_media_id_file_id    ON "public.media_file" ("media_id", "file_id");
CREATE INDEX idx_media_file_media_id                    ON "public.media_file" ("media_id");
CREATE INDEX idx_media_file_file_type_id                ON "public.media_file" ("file_type_id");

ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk0"
        FOREIGN KEY ("media_id") REFERENCES "public.media" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk1"
        FOREIGN KEY ("file_id") REFERENCES "public.file" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk2"
        FOREIGN KEY ("file_type_id") REFERENCES "public.file_type" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;