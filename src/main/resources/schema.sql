-- Drop existing tables if they exist (optional)  
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;


CREATE TABLE employees (
    id SERIAL PRIMARY KEY,          -- Auto-incrementing primary key  
    name VARCHAR(100) NOT NULL,     -- User's name  
    email VARCHAR(100) UNIQUE NOT NULL, -- User's email, must be unique  
    password VARCHAR(255) NOT NULL,  -- User's password  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp for when the user was created  
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Auto-updating timestamp
);

CREATE TABLE users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);
