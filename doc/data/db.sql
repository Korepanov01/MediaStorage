CREATE TABLE "public.media"
(
    "id"            serial       NOT NULL UNIQUE,
    "user_id"       bigint       NOT NULL,
    "category_id"   bigint       NOT NULL,
    "name"          varchar(200) NOT NULL,
    "description"   varchar(10000),
    "media_type_id" bigint       NOT NULL,
    "created_at"    TIMESTAMP    NOT NULL,
    "edited_at"     TIMESTAMP    NOT NULL,
    CONSTRAINT "media_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.tag"
(
    "id"   serial       NOT NULL UNIQUE,
    "name" varchar(200) NOT NULL UNIQUE,
    CONSTRAINT "tag_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.file"
(
    "id"        serial       NOT NULL UNIQUE,
    "path"      varchar(200) NOT NULL UNIQUE,
    "size"      smallint     NOT NULL,
    "extension" varchar(20),
    CONSTRAINT "file_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.media_tag"
(
    "media_id" bigint NOT NULL,
    "tag_id"   bigint NOT NULL
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.user"
(
    "id"            serial       NOT NULL UNIQUE,
    "name"          varchar(200) NOT NULL UNIQUE,
    "password_hash" varchar(16)  NOT NULL,
    "email"         varchar(500) NOT NULL UNIQUE,
    CONSTRAINT "user_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.category"
(
    "id"                 serial       NOT NULL UNIQUE,
    "name"               varchar(200) NOT NULL UNIQUE,
    "parent_category_id" bigint,
    CONSTRAINT "category_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.role"
(
    "id"   serial       NOT NULL UNIQUE,
    "name" varchar(100) NOT NULL UNIQUE,
    CONSTRAINT "role_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.user_role"
(
    "role_id" bigint NOT NULL UNIQUE,
    "user_id" bigint NOT NULL UNIQUE
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.media_file"
(
    "media_id"     bigint NOT NULL UNIQUE,
    "file_id"      bigint NOT NULL UNIQUE,
    "file_type_id" bigint NOT NULL UNIQUE
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.file_type"
(
    "id"   serial       NOT NULL UNIQUE,
    "type" varchar(100) NOT NULL UNIQUE,
    CONSTRAINT "file_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


CREATE TABLE "public.media_type"
(
    "id"   serial       NOT NULL UNIQUE,
    "name" varchar(100) NOT NULL UNIQUE,
    CONSTRAINT "media_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );


ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk0" FOREIGN KEY ("user_id") REFERENCES "public.user" ("id");
ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk1" FOREIGN KEY ("category_id") REFERENCES "public.category" ("id");
ALTER TABLE "public.media"
    ADD CONSTRAINT "media_fk2" FOREIGN KEY ("media_type_id") REFERENCES "public.media_type" ("id");

ALTER TABLE "public.media_tag"
    ADD CONSTRAINT "media_tag_fk0" FOREIGN KEY ("media_id") REFERENCES "public.media" ("id");
ALTER TABLE "public.media_tag"
    ADD CONSTRAINT "media_tag_fk1" FOREIGN KEY ("tag_id") REFERENCES "public.tag" ("id");

ALTER TABLE "public.category"
    ADD CONSTRAINT "category_fk0" FOREIGN KEY ("parent_category_id") REFERENCES "public.category" ("id");

ALTER TABLE "public.user_role"
    ADD CONSTRAINT "user_role_fk0" FOREIGN KEY ("role_id") REFERENCES "public.role" ("id");
ALTER TABLE "public.user_role"
    ADD CONSTRAINT "user_role_fk1" FOREIGN KEY ("user_id") REFERENCES "public.user" ("id");

ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk0" FOREIGN KEY ("media_id") REFERENCES "public.media" ("id");
ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk1" FOREIGN KEY ("file_id") REFERENCES "public.file" ("id");
ALTER TABLE "public.media_file"
    ADD CONSTRAINT "media_file_fk2" FOREIGN KEY ("file_type_id") REFERENCES "public.file_type" ("id");


CREATE INDEX idx_role_id ON "public.role" ("id");
CREATE INDEX idx_role_name ON "public.role" ("name");

CREATE INDEX idx_user_role_user_id ON "public.user_role" ("user_id");

CREATE INDEX idx_user_id ON "public.user" ("id");

CREATE INDEX idx_media_id ON "public.media" ("id");
CREATE INDEX idx_media_name ON "public.media" ("name");
CREATE INDEX idx_media_media_type_id ON "public.media" ("media_type_id");
CREATE INDEX idx_media_category_id ON "public.media" ("category_id");
CREATE INDEX idx_media_created_at ON "public.media" ("created_at");
CREATE INDEX idx_media_edited_at ON "public.media" ("edited_at");

CREATE INDEX idx_media_type_id ON "public.media_type" ("id");

CREATE INDEX idx_tag_id ON "public.tag" ("id");
CREATE INDEX idx_tag_name ON "public.tag" ("name");

CREATE INDEX idx_media_tag_media_id ON "public.media_tag" ("media_id");
CREATE INDEX idx_media_tag_tag_id ON "public.media_tag" ("tag_id");

CREATE INDEX idx_category_id ON "public.category" ("id");
CREATE INDEX idx_category_name ON "public.category" ("name");
CREATE INDEX idx_category_parent_category_id ON "public.category" ("parent_category_id");

CREATE INDEX idx_file_id ON "public.file" ("id");

CREATE INDEX idx_media_file_media_id ON "public.media_file" ("media_id");

CREATE INDEX idx_file_type_id ON "public.file_type" ("id");