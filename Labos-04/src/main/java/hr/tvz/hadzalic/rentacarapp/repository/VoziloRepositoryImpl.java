package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class VoziloRepositoryImpl implements VoziloRepository{
    private static List<Vozilo> voziloList = new ArrayList<>();

    static {
        voziloList.add(new Vozilo(1L,  "ZG9891-GV","2DJRN5DG9ARP44306", 5, "standard gearbox", "standard A/C", 5, "diesel",
                LocalDate.of(2022,2,2), LocalDate.of(2023,2,2), 1000.2 ));
        voziloList.add(new Vozilo(2L, "KA3232-OM", "WP0ZZZ99ZTS392124", 2, "sport gearbox", "sport A/C", 3, "diesel",
                LocalDate.of(2022,2,2), LocalDate.of(2023,2,2), 22000.1));
    }
    @Override
    public List<Vozilo> findAll() {
        return voziloList;
    }

    @Override
    public Optional<Vozilo> findVoziloByCode(String code) {
        return findAll().stream()
                .filter(x -> x.getId().toString().equals(code))
                .findFirst();
    }

    @Override
    public Optional<Vozilo> findVoziloByRegistration(String registration) {
        return findAll().stream()
                .filter(v -> v.getRegistration().equals(registration))
                .findFirst();
    }

    @Override
    public Optional<Vozilo> findVoziloByVin(String vin) {
        return findAll().stream()
                .filter(v -> v.getVin().equals(vin))
                .findFirst();
    }

    @Override
    public Optional<Vozilo> update(Long code, Vozilo vozilo) {
        boolean exists = voziloList.removeIf(
                v -> Objects.equals(v.getVin(), code) &&
                        Objects.equals(v.getId(), vozilo.getId())
        );

        if(exists){
            voziloList.add(vozilo);
            return Optional.of(vozilo);
        } else {
            return Optional.empty();
        }    }

    @Override
    public Optional<Vozilo> save(Vozilo vozilo) {
        vozilo.setId(generateId(voziloList));
        findAll().add(vozilo);
        return Optional.of(vozilo);
    }

    @Override
    public boolean existsByRegistration(String registration) {
        return findAll().stream()
                .filter(q -> q.getRegistration().equals(registration))
                .findFirst()
                .isPresent();
    }

    @Override
    public boolean existsByVin(String vin) {
        return findAll().stream()
                .filter(q -> q.getVin().equals(vin))
                .findFirst()
                .isPresent();
    }

    @Override
    public void delete(Long vehicleCode) {
        voziloList.removeIf(vozilo -> Objects.equals(vozilo.getId(), vehicleCode));
    }

    public Long generateId(List<Vozilo> voziloList) {
        return voziloList.stream()
                .map(Vozilo::getId)
                .max(Long::compare).get() + 1;
    }
}
