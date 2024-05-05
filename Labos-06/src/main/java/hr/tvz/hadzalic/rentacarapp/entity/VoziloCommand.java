package hr.tvz.hadzalic.rentacarapp.entity;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VoziloCommand {
    @NotNull(message = "Id must not be null")
    private Long id;

    @NotNull(message = "Max Number of Passenger must not be null")
    @PositiveOrZero(message = "Max Number of Passenger must be a positive number")
    @Max(value = 6, message = "Max Number of Passenger must not be over 6 Persons")
    private Integer maxNumberOfPassenger;

    @NotEmpty(message = "Shifter must not be empty")
    private String shifter;

    @NotEmpty(message = "A/C must not be empty")
    private String airConditioning;

    @NotNull(message = "Number of door must not be null")
    @Max(value = 5, message = "Max number of doors must not be over 5 doors!")
    @PositiveOrZero(message = "Max number of doors must be a positive number")
    private Integer numberOfDoors;

    @NotNull(message = "Fuel type must not be null")
    private String fuelType;

    @NotNull(message = "Last service date must not be null")
    private LocalDate lastServiceDate;

    @NotNull(message = "Next service date must not be null")
    private LocalDate nextServiceDate;

    @NotNull(message = "Mileage must not be null")
    @PositiveOrZero(message = "Mileage must be a positive number")
    private Double mileage;

    @NotEmpty(message = "Registration must not be empty")
    @Pattern(regexp = "^[A-Z0-9]+ [-] [A-Z0-9]+$", message = "Registration must be in format XXXXX - YYY (uppercase letters and numbers)")
    private String registration;

    @NotNull(message = "Vehicle Identification Number must not be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Vehicle Identification Number must contain only numbers (0-9) or characters (a-z or A-Z)")
    private String vin;

    private List<Review> reviews;
}
