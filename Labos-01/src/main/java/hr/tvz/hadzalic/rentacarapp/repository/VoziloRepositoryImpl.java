package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VoziloRepositoryImpl implements VoziloRepository{
    @Override
    public List<Vozilo> findAll() {

        List<Vozilo> voziloList = new ArrayList<>();
        voziloList.add(new Vozilo("1", 5, "standard gearbox", "standard A/C", 5, "diesel",
                        LocalDate.of(2022,2,2), LocalDate.of(2023,2,2), 1000.2));
        voziloList.add(new Vozilo("2", 2, "sport gearbox", "sport A/C", 3, "diesel",
                        LocalDate.of(2022,2,2), LocalDate.of(2023,2,2), 22000.1));

        return voziloList;
    }

    @Override
    public Optional<Vozilo> findVoziloByCode(String code) {
        return findAll().stream()
                .filter(x -> x.getVehicleCode().equals(code))
                .findFirst();
    }
}
