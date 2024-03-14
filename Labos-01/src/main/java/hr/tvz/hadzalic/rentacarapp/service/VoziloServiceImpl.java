package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.repository.VoziloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoziloServiceImpl implements VoziloService {

    private VoziloRepository voziloRepository;

    @Override
    public List<VoziloDTO> findAll() {
        return voziloRepository.findAll().stream()
                .map(this::convertVoziloToVoziloDTO)
                .toList();
    }

    @Override
    public VoziloDTO findVoziloByCode(String code) {
        return voziloRepository.findAll().stream().filter(q->q.getVehicleCode().equals(code))
                .findFirst().map(this::convertVoziloToVoziloDTO).get();
    }

    private VoziloDTO convertVoziloToVoziloDTO(Vozilo vozilo) {
        return new VoziloDTO(vozilo.getMaxNumberOfPassenger(), vozilo.getGearbox(),
                vozilo.getAirConditioning(), vozilo.getNumberOfDoors(), vozilo.getFuelType(),
                vozilo.getMileage() < 5000);
    }
}
