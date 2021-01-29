DROP TABLE IF EXISTS order_inventory;
CREATE TABLE order_inventory AS SELECT * FROM CSVREAD('classpath:order_inventory.csv');