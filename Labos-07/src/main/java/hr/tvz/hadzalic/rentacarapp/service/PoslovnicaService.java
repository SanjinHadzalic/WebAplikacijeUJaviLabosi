package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.PoslovnicaCommand;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;

import java.util.List;
import java.util.Optional;

public interface PoslovnicaService {
    List<PoslovnicaDTO> findAll();
    Optional<PoslovnicaDTO> findPoslovnicaByID(String id);

    Optional<PoslovnicaDTO> save(PoslovnicaCommand command);
    void delete(Long id);

}
