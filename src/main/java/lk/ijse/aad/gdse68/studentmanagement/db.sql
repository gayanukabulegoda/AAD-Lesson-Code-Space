create database if not exists Student_Management_AAD;

use Student_Management_AAD;

create  table student (
    id VARCHAR(60) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(20) NOT NULL,
    city VARCHAR(20) NOT NULL,
    level VARCHAR(20) NOT NULL
)
