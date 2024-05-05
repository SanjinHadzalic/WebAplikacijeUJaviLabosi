-- ALTER TABLE review DROP CONSTRAINT FKLS0WMPVSKQHK0ECKSTXV94KD3;

DROP TABLE IF EXISTS vozilo;

CREATE TABLE vozilo (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        registration VARCHAR(255) NOT NULL,
                        vin VARCHAR(255) NOT NULL,
                        max_Number_Of_Passenger INT NOT NULL,
                        shifter VARCHAR(255) NOT NULL,
                        air_conditioning VARCHAR(255) NOT NULL,
                        number_Of_Doors INT NOT NULL,
                        fuel_Type VARCHAR(255) NOT NULL,
                        last_Service_Date DATE NOT NULL,
                        next_Service_Date DATE NOT NULL,
                        mileage DOUBLE NOT NULL
);

DROP TABLE IF EXISTS review;
CREATE TABLE review (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        text VARCHAR(1000) NOT NULL,
                        grade INT NOT NULL,
                        vozilo_id BIGINT,
                        FOREIGN KEY (vozilo_id) REFERENCES vozilo(id)
);
