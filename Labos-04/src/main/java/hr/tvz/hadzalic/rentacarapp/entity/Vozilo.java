package hr.tvz.hadzalic.rentacarapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Vozilo {
    private Long id;
    private String registration;
    private String vin;
    private Integer maxNumberOfPassenger;
    private String shifter;
    private String airConditioning;
    private Integer numberOfDoors;
    private String fuelType;
    private LocalDate lastServiceDate;
    private LocalDate nextSeviceDate;
    private Double mileage;
}
