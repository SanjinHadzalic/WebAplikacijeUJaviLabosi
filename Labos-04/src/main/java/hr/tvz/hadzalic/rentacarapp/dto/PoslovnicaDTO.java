package hr.tvz.hadzalic.rentacarapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoslovnicaDTO {

    private String name;
    private String adress;
    private Integer numberOfEmployees;
    private LocalDate date;
}
