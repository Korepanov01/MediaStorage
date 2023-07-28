drop schema if exists public cascade;

create schema "public";

CREATE TABLE "public.media"
(
    "id"            bigserial       CONSTRAINT "media_pk" PRIMARY KEY,
    "user_id"       bigint          NOT NULL,
    "category_id"   bigint          NOT NULL,
    "name"          varchar(200)    NOT NULL,
    "description"   varchar(10000),
    "media_type_id" bigint          NOT NULL,
    "created_at"    TIMESTAMP       NOT NULL,
    "edited_at"     TIMESTAMP       NOT NULL
);

CREATE INDEX idx_media_name             ON "public.media" ("name");
CREATE INDEX idx_media_media_type_id    ON "public.media" ("media_type_id");
CREATE INDEX idx_media_category_id      ON "public.media" ("category_id");
CREATE INDEX idx_media_created_at       ON "public.media" ("created_at");
CREATE INDEX idx_media_edited_at        ON "public.media" ("edited_at");


CREATE TABLE "public.tag"
(
    "id"    bigserial       CONSTRAINT "tag_pk" PRIMARY KEY,
    "name"  varchar(200)    NOT NULL
);

CREATE UNIQUE INDEX uidx_tag_name ON "public.tag"(name);


CREATE TABLE "public.file"
(
    "id"            bigserial       CONSTRAINT "file_pk" PRIMARY KEY,
    "name"          varchar(200)    NOT NULL,
    "content_type"  varchar(200)    NOT NULL,
    "size"          BIGINT          NOT NULL,
    "data"          bytea           NOT NULL
);


CREATE TABLE "public.media_tag"
(
    "id"       bigserial    CONSTRAINT "media_tag_pk" PRIMARY KEY,
    "media_id" bigint       NOT NULL,
    "tag_id"   bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_tag_media_id_tag_id ON "public.media_tag" ("media_id", "tag_id");
CREATE INDEX idx_media_tag_media_id ON "public.media_tag" ("media_id");
CREATE INDEX idx_media_tag_tag_id   ON "public.media_tag" ("tag_id");


CREATE TABLE "public.user"
(
    "id"                bigserial       CONSTRAINT "user_pk" PRIMARY KEY,
    "name"              varchar(200)    NOT NULL,
    "password_hash"     varchar(256)    NOT NULL,
    "email"             varchar(500)    NOT NULL
);

CREATE UNIQUE INDEX uidx_user_name     ON "public.user" ("name");
CREATE UNIQUE INDEX uidx_user_email    ON "public.user" ("email");


CREATE TABLE "public.category"
(
    "id"                 bigserial      CONSTRAINT "category_pk" PRIMARY KEY,
    "name"               varchar(200)   NOT NULL,
    "parent_category_id" bigint
);

CREATE UNIQUE INDEX uidx_category_name          ON "public.category" ("name");
CREATE INDEX idx_category_parent_category_id    ON "public.category" ("parent_category_id");


CREATE TABLE "public.role"
(
    "id"   bigserial    CONSTRAINT "role_pk" PRIMARY KEY,
    "name" varchar(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_role_name ON "public.role" ("name");


CREATE TABLE "public.user_role"
(
    "id"        bigserial   CONSTRAINT "user_role_pk" PRIMARY KEY,
    "role_id"   bigint      NOT NULL,
    "user_id"   bigint      NOT NULL
);

CREATE UNIQUE INDEX uidx_user_role_role_id_role_id ON "public.user_role" ("role_id", "user_id");
CREATE INDEX idx_user_role_user_id ON "public.user_role" ("user_id");


CREATE TABLE "public.media_file"
(
    "id"           bigserial    CONSTRAINT "media_file_pk" PRIMARY KEY,
    "media_id"     bigint       NOT NULL,
    "file_id"      bigint       NOT NULL,
    "file_type_id" bigint       NOT NULL
);

CREATE UNIQUE INDEX uidx_media_file_media_id_file_id ON "public.media_file" ("media_id", "file_id");
CREATE INDEX idx_media_file_media_id        ON "public.media_file" ("media_id");
CREATE INDEX idx_media_file_file_type_id    ON "public.media_file" ("file_type_id");


CREATE TABLE "public.file_type"
(
    "id"   bigserial    CONSTRAINT "file_type_pk" PRIMARY KEY,
    "name" varchar(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_file_type_type ON "public.file_type" ("name");


CREATE TABLE "public.media_type"
(
    "id"   bigserial    CONSTRAINT "media_type_pk" PRIMARY KEY,
    "name" varchar(100) NOT NULL
);

CREATE UNIQUE INDEX uidx_media_type_name ON "public.media_type" ("name");


--Внешние ключи для public.media
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

--Внешние ключи для public.media_tag
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

--Foreign keys for public.category
ALTER TABLE "public.category"
    ADD CONSTRAINT "category_fk0"
        FOREIGN KEY ("parent_category_id") REFERENCES "public.category" ("id")
            ON DELETE CASCADE
            ON UPDATE CASCADE;

--Foreign keys for public.user_role
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

--Foreign keys for public.media_file
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