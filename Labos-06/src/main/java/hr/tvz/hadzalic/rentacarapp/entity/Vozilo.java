package hr.tvz.hadzalic.rentacarapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
//@Table(name = "vozilo")
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
}