--MySQL Dialect
CREATE DATABASE library;
CREATE TABLE book (
	title VARCHAR(256) NOT NULL,
	author VARCHAR(128) NOT NULL,
	published DATE,
	isbn VARCHAR(32) NOT NULL UNIQUE,
	category VARCHAR(128),
	page_count INT,
	publisher VARCHAR(128) DEFAULT 'nieznana',
	price FLOAT,
	in_stock INT DEFAULT 0
);

CREATE DATABASE jdbc_company;
CREATE TABLE employee(
    id INT,
    first_name VARCHAR(100),
    last_name VARCHAR(200),
    salary FLOAT,
    employment_date DATE,
    title VARCHAR(100),
    PRIMARY KEY (id)
);


