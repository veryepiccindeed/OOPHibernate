CREATE DATABASE hibernate_test;

USE hibernate_test;

CREATE TABLE students (
    id INT not null AUTO_INCREMENT,
    name varchar(50) not null,
    age int not null,
    major varchar(50) not null,
    PRIMARY KEY (id)
);