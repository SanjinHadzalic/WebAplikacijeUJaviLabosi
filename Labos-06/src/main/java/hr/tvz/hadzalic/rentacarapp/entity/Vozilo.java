package hr.tvz.hadzalic.rentacarapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "vozilo")
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "vozilo_seq", sequenceName = "VOZILO_SEQ", allocationSize = 1)
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

    public Vozilo(Long id, Integer maxNumberOfPassenger, String shifter, String airConditioning, Integer numberOfDoors, String fuelType, LocalDate lastServiceDate, LocalDate nextServiceDate, Double mileage, String registration, String vin) {
    }

    public Vozilo() {

    }
}