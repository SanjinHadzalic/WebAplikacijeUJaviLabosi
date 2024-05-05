package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;

import java.util.List;
import java.util.Optional;

public interface VoziloService {
    List<VoziloDTO> findAll();
    List<Vozilo> findNext();
    List<Vozilo> fetchAll();
    VoziloDTO findVoziloByCode(String code);
    Vozilo findVoziloByID(Long id);
    Optional<VoziloDTO> save(VoziloCommand command);
    Optional<VoziloDTO> findVoziloByRegistration(String registration);
    Optional<VoziloDTO> findVoziloByVin(String vin);
    Optional<VoziloDTO> update(Long code, VoziloCommand command);
    Optional<Vozilo> updateVozilo(Long id, Vozilo vozilo);
    void delete(Long vehicleCode);
}
