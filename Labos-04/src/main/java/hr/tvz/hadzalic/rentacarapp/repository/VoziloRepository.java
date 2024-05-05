package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;

import java.util.List;
import java.util.Optional;

public interface VoziloRepository {
    List<Vozilo> findAll();
    Optional<Vozilo> findVoziloByCode(String code);
    Optional<Vozilo> findVoziloByRegistration(String registration);
    Optional<Vozilo> findVoziloByVin(String vin);
    Optional<Vozilo> update(Long code, Vozilo vozilo);
    Vozilo updateVozilo(Long id, Vozilo vozilo);
    Optional<Vozilo> save(Vozilo vozilo);
    boolean existsByRegistration(String registration);
    boolean existsByVin(String vin);
    void delete(Long vehicleCode);
}
