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

DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

DROP TABLE IF EXISTS authority;
CREATE TABLE authority (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_authority(
    user_id BIGINT,
    authority_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (authority_id) REFERENCES authority(id),
    PRIMARY KEY (user_id, authority_id)
);

CREATE TABLE IF NOT EXISTS REFRESH_TOKEN (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     token VARCHAR(255),
     expiry_date TIMESTAMP,
     user_id BIGINT,
     FOREIGN KEY (user_id) REFERENCES USERS(id)
    );