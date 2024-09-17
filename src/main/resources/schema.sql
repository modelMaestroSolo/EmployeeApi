-- Drop existing tables if they exist (optional)  
DROP TABLE IF EXISTS employees CASCADE ;

-- Create the users table  
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,          -- Auto-incrementing primary key  
    name VARCHAR(100) NOT NULL,     -- User's name  
    email VARCHAR(100) UNIQUE NOT NULL, -- User's email, must be unique  
    password VARCHAR(255) NOT NULL,  -- User's password  
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Timestamp for when the user was created  
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Auto-updating timestamp
); 