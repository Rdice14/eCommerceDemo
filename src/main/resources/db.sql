DROP DATABASE IF EXISTS e_commerce_demo;

CREATE DATABASE e_commerce_demo;

USE e_commerce_demo;

CREATE TABLE IF NOT EXISTS PRODUCTS (
    id INT auto_increment PRIMARY KEY,
    name varchar(255),
    price double,
    category varchar(255)
    );

CREATE TABLE IF NOT EXISTS CART (
    id INT auto_increment PRIMARY KEY,
    product_list VARCHAR(255),
    total_price double
    );