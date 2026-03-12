CREATE TABLE IF NOT EXISTS posts (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    body TEXT NOT NULL,
    user_id INT NOT NULL
);

INSERT INTO posts (id, title, body, user_id) VALUES
(1, 'Title test', 'Body Test', 1),
(2, 'Post 2', 'Body 1', 1),
(3, 'Post 3', 'Body 2', 2);