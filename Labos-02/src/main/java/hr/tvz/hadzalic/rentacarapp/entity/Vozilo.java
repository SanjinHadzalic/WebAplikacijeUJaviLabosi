package hr.tvz.hadzalic.rentacarapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Vozilo {
    private String vehicleCode;
    private Integer maxNumberOfPassenger;
    private String gearbox;
    private String airConditioning;
    private Integer numberOfDoors;
    private String fuelType;
    private LocalDate lastServiceDate;
    private LocalDate nextSeviceDate;
    private Double mileage;
}
