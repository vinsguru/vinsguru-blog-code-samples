DROP TABLE IF EXISTS purchase_order;
CREATE TABLE purchase_order (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id INT,
    product_id INT,
    price NUMERIC(10, 2),
    status VARCHAR(50)
);