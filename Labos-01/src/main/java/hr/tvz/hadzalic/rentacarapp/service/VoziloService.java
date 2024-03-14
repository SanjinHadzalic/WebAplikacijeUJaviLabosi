package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;

import java.util.List;

public interface VoziloService {
    List<VoziloDTO> findAll();
    VoziloDTO findVoziloByCode(String code);
}
