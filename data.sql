-- create this file in resources folder 
DROP TABLE IF EXISTS Product;
CREATE TABLE IF NOT EXISTS Product(
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    description varchar(255),
    vendor varchar(255),
    price int,
    stock int,
    currency varchar(255),
    image_url varchar(255),
    sku varchar(255) UNIQUE
);