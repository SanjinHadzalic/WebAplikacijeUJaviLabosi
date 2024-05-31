package hr.tvz.hadzalic.rentacarapp.repository;

import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Poslovnica;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;

import java.util.List;
import java.util.Optional;

public interface PoslovnicaRepository {

    List<Poslovnica> findAll();
    Optional<Poslovnica> findPoslovnicaByID(String id);
    Optional<Poslovnica> save(Poslovnica poslovnica);
    void delete(Long id);

}
