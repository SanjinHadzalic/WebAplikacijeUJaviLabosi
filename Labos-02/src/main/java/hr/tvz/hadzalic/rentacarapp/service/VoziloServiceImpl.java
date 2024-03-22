package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;
import hr.tvz.hadzalic.rentacarapp.repository.VoziloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<VoziloDTO> findVoziloByRegistration(String registration) {
        return voziloRepository.findAll().stream()
                .filter(q -> q.getRegistracija().equals(registration))
                .findFirst()
                .map(this::convertVoziloToVoziloDTO);
    }

    @Override
    public Optional<VoziloDTO> findVoziloByVin(String vin) {
        return voziloRepository.findAll().stream()
                .filter(q -> q.getBrojSasije().equals(vin))
                .findFirst()
                .map(this::convertVoziloToVoziloDTO);
    }

    @Override
    public Optional<VoziloDTO> update(Long code, VoziloCommand command) {
        return voziloRepository.update(code, mapCommandToVozilo(command))
                .map(this::convertVoziloToVoziloDTO);
    }

    @Override
    public Optional<VoziloDTO> save(VoziloCommand command) {
        if(hasDuplicateRegistrationOrVin(command)) {
            return Optional.empty();
        }
        return voziloRepository.save(mapCommandToVozilo(command)).map(this::mapVoziloToDTO);
    }

    @Override
    public void delete(Long vehicleCode) {
        voziloRepository.delete(vehicleCode);
    }

    private boolean hasDuplicateRegistrationOrVin(VoziloCommand command) {
        return voziloRepository.existsByRegistration(command.getRegistration()) ||
                voziloRepository.existsByVin(command.getVehicleIdentificationNumber());
    }

    private VoziloDTO convertVoziloToVoziloDTO(Vozilo vozilo) {
        return new VoziloDTO(vozilo.getMaxNumberOfPassenger(), vozilo.getGearbox(),
                vozilo.getAirConditioning(), vozilo.getNumberOfDoors(), vozilo.getFuelType(),
                vozilo.getMileage() < 5000);
    }

    private Vozilo mapCommandToVozilo(final VoziloCommand command) {
        return new Vozilo(
                command.getVehicleCode(),
                command.getMaxNumberOfPassenger(),
                command.getGearbox(),
                command.getAirConditioning(),
                command.getNumberOfDoors(),
                command.getFuelType(),
                command.getLastServiceDate(),
                command.getNextSeviceDate(),
                command.getMileage(),
                command.getRegistration(),
                command.getVehicleIdentificationNumber()
        );
    }

    private VoziloDTO mapVoziloToDTO(final Vozilo vozilo) {
        return new VoziloDTO(
                vozilo.getMaxNumberOfPassenger(),
                vozilo.getGearbox(),
                vozilo.getAirConditioning(),
                vozilo.getNumberOfDoors(),
                vozilo.getFuelType(),
                vozilo.getMileage() < 5000
        );
    }
}
