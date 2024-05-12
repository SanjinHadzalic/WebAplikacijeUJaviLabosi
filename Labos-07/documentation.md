# Repetitorij

- Ako tijekom **POST metode** dolazi do org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException, iako je id različit od
ostalih idjeva u bazi podataka

`@Data
@Entity
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration")
    private String registration;

    @Column(name = "vin")
    private String vin;

    @Column(name = "max_Number_Of_Passenger")
    private Integer maxNumberOfPassenger;

    @Column(name = "shifter")
    private String shifter;

    @Column(name = "air_Conditioning")
    private String airConditioning;

    @Column(name = "number_Of_Doors")
    private Integer numberOfDoors;

    @Column(name = "fuel_Type")
    private String fuelType;

    @Column(name = "last_Service_Date")
    private LocalDate lastServiceDate;

    @Column(name = "next_Service_Date")
    private LocalDate nextServiceDate;

    @Column(name = "mileage")
    private Double mileage;

    @OneToMany(mappedBy = "vozilo", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;

    public Vozilo(Long id, String registration, String vin, Integer maxNumberOfPassenger, String shifter, String airConditioning, Integer numberOfDoors, String fuelType, LocalDate lastServiceDate, LocalDate nextServiceDate, Double mileage, List<Review> reviews) {
        this.id = id;
        this.registration = registration;
        this.vin = vin;
        this.maxNumberOfPassenger = maxNumberOfPassenger;
        this.shifter = shifter;
        this.airConditioning = airConditioning;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.lastServiceDate = lastServiceDate;
        this.nextServiceDate = nextServiceDate;
        this.mileage = mileage;
        this.reviews = reviews;
    }

    public Vozilo() {

    }
}`

- Class Vozilo neka bude kao u primjeru iznad, a schema.sql:

`-- ALTER TABLE review DROP CONSTRAINT FKLS0WMPVSKQHK0ECKSTXV94KD3;

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
`

- U datoteci data.sql nije potrebno dodavati varijablu id, jer ce JPA sam to obaviti:

`INSERT INTO VOZILO( registration, vin, max_Number_Of_Passenger, shifter, air_Conditioning, number_Of_Doors, fuel_Type,
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
`

- Unutar frontend sloja aplikacije (Angular) najbolja praksa je varijable/paramatre nazvati isto onakvima kako se zovu 
u backend sloju aplikacije (Spring Boot) da ne bi doslo do nepredvidljivih gresaka tijekom POST metode (baca null 
vrijednost umjesto Object)