package hr.tvz.hadzalic.rentacarapp.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public class VoziloCommand {
    @NotNull(message = "Vehicle Code must not be null")
    private String vehicleCode;

    @NotNull(message = "Max Number of Passenger must not be null")
    @PositiveOrZero(message = "Max Number of Passenger must be a positive number")
    @Max(value = 6, message = "Max Number of Passenger must not be over 6 Persons")
    private Integer maxNumberOfPassenger;

    @NotEmpty(message = "Gearbox must not be empty")
    private String gearbox;

    @NotEmpty(message = "A/C must not be empty")
    private String airConditioning;

    @NotNull(message = "Number of door must not be null")
    @Max(value = 5, message = "Max number of doors must not be over 5 doors!")
    @PositiveOrZero(message = "Max number of doors must be a positive number")
    private Integer numberOfDoors;

    private String fuelType;

    private LocalDate lastServiceDate;

    private LocalDate nextSeviceDate;

    private Double mileage;

    private String registration;

    private String vehicleIdentificationNumber;

}
