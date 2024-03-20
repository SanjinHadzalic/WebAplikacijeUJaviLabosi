package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;

import java.util.List;
import java.util.Optional;

public interface VoziloRepository {
    List<Vozilo> findAll();
    Optional<Vozilo> findVoziloByCode(String code);
}
