DROP TABLE IF EXISTS tj_media;
DROP TABLE IF EXISTS tr_accidents_rules;
DROP TABLE IF EXISTS tj_accidents;
DROP TABLE IF EXISTS tz_users;
DROP TABLE IF EXISTS tz_roles;
DROP TABLE IF EXISTS tz_accident_stats;
DROP TABLE IF EXISTS tz_rules;
DROP TABLE IF EXISTS tz_accident_types;

DROP SEQUENCE IF EXISTS tz_accident_types_id_seq;
DROP SEQUENCE IF EXISTS tz_rules_id_seq;
DROP SEQUENCE IF EXISTS tz_accident_types_id_seq;
DROP SEQUENCE IF EXISTS tz_users_id_seq;
DROP SEQUENCE IF EXISTS tj_accidents_id_seq;
DROP SEQUENCE IF EXISTS tj_media_id_seq;

CREATE TABLE tz_accident_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE tz_rules (
    id SERIAL PRIMARY KEY,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE tz_accident_stats (
   id INTEGER PRIMARY KEY,
   name VARCHAR(120) NOT NULL
);

CREATE TABLE tz_roles (
    id INTEGER PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(120) NOT NULL
);

CREATE TABLE tz_users (
    id SERIAL PRIMARY KEY,
    id_role INTEGER DEFAULT 1 REFERENCES tz_roles (id) ON DELETE RESTRICT,
    enabled BOOLEAN DEFAULT TRUE,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    pass VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE tj_accidents (
    id SERIAL PRIMARY KEY,
    id_type INTEGER REFERENCES tz_accident_types (id) ON DELETE SET NULL,
    id_state INTEGER NOT NULL DEFAULT 1 REFERENCES tz_accident_stats (id) ON DELETE RESTRICT,
    id_author INTEGER NOT NULL REFERENCES tz_users (id) ON DELETE RESTRICT,
    id_inspector INTEGER DEFAULT NULL REFERENCES tz_users (id) ON DELETE SET NULL,
    name VARCHAR(250) NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT current_timestamp,
    actor1number VARCHAR(16),
    actor2number VARCHAR(16),
    address TEXT,
    description TEXT,
    solution TEXT
);

CREATE TABLE tr_accidents_rules (
    id_accident INTEGER NOT NULL REFERENCES tj_accidents (id) ON DELETE CASCADE,
    id_rule INTEGER NOT NULL REFERENCES tz_rules (id) ON DELETE CASCADE,
    PRIMARY KEY (id_accident, id_rule)
);

CREATE TABLE tj_media (
    id SERIAL PRIMARY KEY,
    id_accident INTEGER NOT NULL REFERENCES tj_accidents (id) ON DELETE CASCADE,
    is_video SMALLINT DEFAULT 0,
    mimeType VARCHAR(60) NOT NULL
);