
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    product VARCHAR(255),
    quantity INTEGER,
    username VARCHAR(255)
);
