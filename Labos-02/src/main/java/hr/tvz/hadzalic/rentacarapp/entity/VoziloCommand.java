package hr.tvz.hadzalic.rentacarapp.entity;

import jakarta.validation.constraints.*;

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

    @NotNull(message = "Fuel type must not be null")
    private String fuelType;

    @NotBlank(message = "Last service date must not be blank")
    private LocalDate lastServiceDate;

    @NotBlank(message = "Next service date must not be blank")
    private LocalDate nextSeviceDate;

    @NotNull(message = "Mileage must not be null")
    @PositiveOrZero(message = "Mileage must be a positive number")
    private Double mileage;

    @NotEmpty(message = "Registration must not be empty")
    @Pattern(regexp = "-", message = "Registration must contain a hyphen")
    private String registration;

    @NotNull(message = "Vehicle Identification Number must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Vehicle Identification Number must contain only numbers (0-9) or characters (a-z or A-Z)")
    private String vehicleIdentificationNumber;

}
