package hr.tvz.hadzalic.rentacarapp.dto;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoziloDTO {
    private Integer maxNumberOfPassenger;
    private String shifter;
    private String airConditioning;
    private Integer numberOfDoors;
    private String fuelType;
    private Boolean newCar;

    public static VoziloDTO newVehicleDTO(Vozilo vozilo) {
        return new VoziloDTO(
                vozilo.getMaxNumberOfPassenger(),
                vozilo.getGearbox(),
                vozilo.getAirConditioning(),
                vozilo.getNumberOfDoors(),
                vozilo.getFuelType(),
                vozilo.getMileage() < 5000
        );
    }
}
