DROP DATABASE IF EXISTS carRentalDB;
CREATE DATABASE carRentalDB;

USE carRentalDB;

CREATE TABLE Make(
  makeId INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  makeName VARCHAR(45) NOT NULL);

CREATE TABLE Model (
  modelId INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  modelName VARCHAR(45) NOT NULL,
  makeId INT NOT NULL,
FOREIGN KEY fk_makeId(makeId) REFERENCES Make(makeId));

CREATE TABLE Category (
  categoryId INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  categoryName VARCHAR(45) NOT NULL,
  categoryPrice DECIMAL NOT NULL); 

CREATE TABLE Vehicle (
  licensePlate VARCHAR(45) PRIMARY KEY NOT NULL UNIQUE,
  categoryId INT NOT NULL,
  modelId INT NOT NULL,
  color VARCHAR(45) NOT NULL,
  FOREIGN KEY fk_categoryId(categoryId) REFERENCES Category(categoryId));  

CREATE TABLE Customer (
  customerId INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  firstName VARCHAR(45) NOT NULL,
  lastName VARCHAR(45) NOT NULL,
  dateOfBirth DATE NOT NULL,
  licenseNumber VARCHAR(45) NOT NULL,
  loyaltyPoints INT NOT NULL);

CREATE TABLE Reservation (
  reservationId INT PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  customerId INT NOT NULL,
  licensePlate VARCHAR(45) NOT NULL,
  startDate DATE NOT NULL,
  beforeTaxPrice DECIMAL NOT NULL,
  endDate DATE NOT NULL,
  discount DECIMAL NOT NULL,
  tax DECIMAL NOT NULL,
  totalPrice DECIMAL NOT NULL,
  FOREIGN KEY fk_customerId(customerId) REFERENCES Customer(customerId),
  FOREIGN KEY fk_licensePlate(licensePlate) REFERENCES Vehicle(licensePlate));
