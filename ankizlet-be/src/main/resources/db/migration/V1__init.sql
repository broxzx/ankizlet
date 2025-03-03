CREATE SCHEMA IF NOT EXISTS public;
CREATE TABLE IF NOT EXISTS public.users
(
    id                       UUID
        CONSTRAINT users_id_pk PRIMARY KEY,
    firstname                VARCHAR(64)
        CONSTRAINT users_firstname_not_null NOT NULL,
    lastname                 VARCHAR(64),
    username                 VARCHAR(64)
        CONSTRAINT users_username_unique UNIQUE,
    password                 VARCHAR(255),
    registration_source_type VARCHAR(255)
        CONSTRAINT users_registration_source_type_not_null NOT NULL,
    telegram_chat_id         VARCHAR(64) CONSTRAINT users_telegram_chat_id_unique UNIQUE,
    email                    VARCHAR(255),
    role                     VARCHAR(255)
        CONSTRAINT users_role_not_null NOT NULL,
    created_at TIMESTAMP CONSTRAINT users_created_at_not_null NOT NULL
);
CREATE INDEX IF NOT EXISTS users_id_idx on users (id);

CREATE TABLE IF NOT EXISTS public.word_collections
(
    id                 UUID
        CONSTRAINT word_collections_id_pk PRIMARY KEY,
    name_of_collection VARCHAR(256)
        CONSTRAINT word_collections_not_null NOT NULL,
    description        TEXT,
    created_at         TIMESTAMP
        CONSTRAINT word_collections_created_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP
        CONSTRAINT word_collections_updated_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id            UUID
        CONSTRAINT word_collections_users_fk REFERENCES public.users (id) ON DELETE CASCADE,
    last_visited       TIMESTAMP
        CONSTRAINT word_collections_last_visited_not_null NOT NULL
);
CREATE INDEX IF NOT EXISTS word_collections_id_idx on word_collections (id);

CREATE TABLE IF NOT EXISTS public.collection_role_users
(
    id                 INTEGER GENERATED ALWAYS AS IDENTITY
        CONSTRAINT collection_role_users_id_pk PRIMARY KEY,
    word_collection_id UUID
        CONSTRAINT collection_role_users_word_collection_id REFERENCES word_collections (id) ON DELETE CASCADE NOT NULL,
    user_id            UUID
        CONSTRAINT collection_role_users_user_id REFERENCES users (id) ON DELETE CASCADE NOT NULL,
    role               varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS public.words
(
    id                 UUID
        CONSTRAINT words_id_pk PRIMARY KEY,
    front              text,
    back               text,
    word_collection_id UUID
        CONSTRAINT word_collections_id_fk REFERENCES word_collections (id) ON DELETE CASCADE NOT NULL,
    user_id            UUID
        CONSTRAINT words_users_fk REFERENCES public.users (id) ON DELETE CASCADE,
    created_at         TIMESTAMP
        CONSTRAINT words_created_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP
        CONSTRAINT words_updated_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP,
    repeat_at          timestamp
);
CREATE INDEX IF NOT EXISTS word_id_idx on words (id);

CREATE TABLE IF NOT EXISTS public.day_streaks
(
    id        INTEGER GENERATED ALWAYS AS IDENTITY
        CONSTRAINT day_streaks_id_pk PRIMARY KEY,
    active_at TIMESTAMP
        CONSTRAINT day_streaks_active_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id   UUID
        CONSTRAINT day_streaks_user_id_fk REFERENCES public.users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.notifications
(
    id                 INTEGER GENERATED ALWAYS AS IDENTITY
        CONSTRAINT notifications_id PRIMARY KEY,
    event_type         varchar(255)
        CONSTRAINT notifications_event_type_not_null NOT NULL,
    user_id            UUID
        CONSTRAINT notifications_user_id_fk REFERENCES public.users (id) ON DELETE CASCADE,
    word_collection_id UUID
        CONSTRAINT notifications_word_collection_id_fk REFERENCES public.word_collections (id) ON DELETE CASCADE,
    created_at         TIMESTAMP
        CONSTRAINT notifications_created_at_not_null NOT NULL DEFAULT CURRENT_TIMESTAMP
);