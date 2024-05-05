package hr.tvz.hadzalic.rentacarapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Poslovnica {
    private Long id;
    private String name;
    private String adress;
    private Integer numberOfEmployees;
    private LocalDate date;
}
