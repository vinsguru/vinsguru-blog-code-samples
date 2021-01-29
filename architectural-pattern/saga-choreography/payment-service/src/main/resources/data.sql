DROP TABLE IF EXISTS user_balance;
CREATE TABLE user_balance AS SELECT * FROM CSVREAD('classpath:user_balance.csv');