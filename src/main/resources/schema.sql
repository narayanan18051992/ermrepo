DROP TABLE IF EXISTS Employee;  
CREATE TABLE Employee(  
id INT PRIMARY KEY,  
name VARCHAR(50) NOT NULL,  
dob VARCHAR(20) NOT NULL,
rating INT,
performance_level VARCHAR(20),
age_category VARCHAR(20)
);  