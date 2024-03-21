package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;

import java.util.List;
import java.util.Optional;

public interface VoziloService {
    List<VoziloDTO> findAll();
    VoziloDTO findVoziloByCode(String code);
    Optional<VoziloDTO> save(VoziloCommand command);
}
