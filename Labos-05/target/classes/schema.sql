DROP TABLE IF EXISTS VOZILO;

CREATE TABLE VOZILO(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    registration VARCHAR(255) NOT NULL,
    vin VARCHAR(255) NOT NULL,
    maxNumberOfPassenger INT NOT NULL,
    shifter VARCHAR(255) NOT NULL,
    airConditioning VARCHAR(255) NOT NULL,
    numberOfDoors INT NOT NULL,
    fuelType VARCHAR(255) NOT NULL,
    lastServiceDate DATE NOT NULL,
    nextServiceDate DATE NOT NULL,
    mileage DOUBLE NOT NULL
)