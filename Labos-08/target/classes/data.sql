INSERT INTO VOZILO( registration, vin, max_Number_Of_Passenger, shifter, air_Conditioning, number_Of_Doors, fuel_Type,
                   last_Service_Date, next_Service_Date, mileage) VALUES
                                                                  ('ZG9891 - GV','2DJRN5DG9ARP44306', 5, 'standard shifter', 'standard A/C', 5, 'diesel',
                                                                   '2022-02-02', '2023-02-02', 1000.2),
                                                                  ('KA3232 - OM','WP0ZZZ99ZTS392124', 5, 'sport shifter', 'standard A/C', 3, 'biodiesel',
                                                                   '2022-02-02', '2023-02-02', 21000.2),
                                                                  ('DA1234 - BJ', 'SR0ZZZ99ZTS391111', 4, 'basic shifter', 'upgraded A/C', 5, 'electricity',
                                                                   '2022-02-02', '2023-02-02', 12343.6);

INSERT INTO review (title, text, grade, vozilo_id)
VALUES
    ('Great Car!', 'The car is amazing. Smooth ride and great fuel efficiency.', 5, 1),
    ('Good Experience', 'Had a pleasant experience with this vehicle.', 4, 2),
    ('Needs Improvement', 'The car could use some maintenance. Issues with brakes.', 3, 1),
    ('Das Auto', 'The best Car you can currently find on car market', 5, 3);

INSERT INTO users(username, password, first_name, last_name) VALUES('user', '$2a$12$wdf2YclvjZhvX8dEMLBH9OueMr00mmNSVSpAeh7VcRt/JzWNmtTpy', 'user', 'user'),
                                            ('admin', '$2a$12$wdf2YclvjZhvX8dEMLBH9OueMr00mmNSVSpAeh7VcRt/JzWNmtTpy', 'admin', 'admin'),
                                            ('john', '$2a$12$wdf2YclvjZhvX8dEMLBH9OueMr00mmNSVSpAeh7VcRt/JzWNmtTpy', 'John', 'Doe');

INSERT INTO authority(name) VALUES('ROLES_USER'),
                                  ('ROLES_ADMIN');

INSERT INTO users_authority(user_id, authority_id) VALUES(1,1),
                                                         (2, 1),
                                                         (2, 2),
                                                         (3, 1);