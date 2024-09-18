CREATE DATABASE IF NOT EXISTS Apartment_Management;
USE Apartment_Management;

CREATE TABLE Apartments (
    apartment_id varchar(255) PRIMARY KEY,
    building_name VARCHAR(255) NOT NULL,
    unit_number VARCHAR(50) NOT NULL,
    area DECIMAL(5,2) NOT NULL,
    num_rooms INT NOT NULL,
    status ENUM('occupied', 'vacant', 'under_maintenance') DEFAULT 'vacant',
    note NVARCHAR(10000)
);

CREATE TABLE role (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name varchar(255) NOT NULL
);

CREATE TABLE user (
    user_id varchar(255) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    gender BOOLEAN,
    dob DATE,
    role_id INT NOT NULL DEFAULT (3),
    status ENUM('ACTIVE', 'INACTIVE', 'BANNED') DEFAULT 'active',
    FOREIGN KEY (role_id) REFERENCES role(role_id) 
);

CREATE TABLE Residents (
    resident_id varchar(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    id_card VARCHAR(50),
    birth_year INT,
    gender VARCHAR(10),
    status ENUM('current', 'former', 'temporary_absent') DEFAULT 'current'
);

CREATE TABLE Contracts (
    contract_id varchar(255) PRIMARY KEY,
    user_id varchar(255), 
    apartment_id varchar(255), 
    resident_id varchar(255),
    start_date DATE NOT NULL,
    end_date DATE,
    contract_status ENUM('active', 'expired', 'terminated') DEFAULT 'active',
    is_representative BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    FOREIGN KEY (apartment_id) REFERENCES Apartments(apartment_id) ON DELETE CASCADE,
    FOREIGN KEY (resident_id) REFERENCES Residents(resident_id) ON DELETE CASCADE
);

CREATE TABLE Billing (
    billing_id varchar(255) PRIMARY KEY,
    apartment_id varchar(255),
    bill_type ENUM('electricity', 'water', 'service', 'parking'),
    usage_amount DECIMAL(8,2) NOT NULL,
    total_amount DECIMAL(10,2),
    created_date DATE,
    due_date DATE,
    payment_status ENUM('unpaid', 'paid', 'overdue') DEFAULT 'unpaid',
    note VARCHAR(255),
    FOREIGN KEY (apartment_id) REFERENCES Apartments(apartment_id) ON DELETE CASCADE
);

CREATE TABLE invalidated_token(
	id varchar(255) PRIMARY KEY,
    expriry_time timestamp
)