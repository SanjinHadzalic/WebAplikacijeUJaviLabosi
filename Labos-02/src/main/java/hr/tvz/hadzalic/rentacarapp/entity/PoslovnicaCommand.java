package hr.tvz.hadzalic.rentacarapp.entity;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PoslovnicaCommand {
    private Long id;
    private String name;
    private String adress;
    @Positive(message = "Number of Employees must be a positive number!")
    @Min(value = 3, message = "Number of Employees must be at least 3")
    private Integer numberOfEmployees;
    @FutureOrPresent(message = "Date must be in the future or present!")
    private LocalDate date;
}
