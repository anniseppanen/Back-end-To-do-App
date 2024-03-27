DROP TABLE IF EXISTS todo;
DROP TABLE IF EXISTS priority;
DROP TABLE IF EXISTS todo_user;

-- Aloitetaan idt aina uudestaan ykkösestä, jos lista on välissä tyhjä
CREATE SEQUENCE IF EXISTS todo_id;
CREATE SEQUENCE todo_id START 1;


CREATE TABLE priority (
    priority_id BIGSERIAL PRIMARY KEY,
    priority BIGINT
);

INSERT INTO priority(priority)
VALUES(1), (2), (3);

CREATE TABLE todo (
    id BIGINT PRIMARY KEY DEFAULT nextval('todo_id'),
    todo_text VARCHAR(200) NOT NULL,
    deadline VARCHAR(15),
    priority_id BIGINT,
    FOREIGN KEY (priority_id) REFERENCES priority(priority_id)
);

CREATE TABLE todo_user (
    user_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password_hash VARCHAR(250) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO todo_user (username, password_hash, role) 
VALUES ('user', '$2a$12$usVKZCf8sy181z.oxwno6ubp6Vnp6ketU4C9u1y6mQFXBTmkp8szG', 'USER'), 
       ('admin', '$2a$12$moydUO8UiNAilmXQVDXZbOBB3pwGNcx4IizV1EHMMKnxj2SAAViey', 'ADMIN');

-- ChatGPTllä luotu trigger, joka aloittaa idt aina ykkösestä, jos taulu on tyhjä
CREATE OR REPLACE FUNCTION reset_todo_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM todo) THEN
        ALTER SEQUENCE todo_id RESTART WITH 1;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_reset_todo_id
AFTER DELETE ON todo
FOR EACH STATEMENT
EXECUTE FUNCTION reset_todo_id();