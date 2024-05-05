package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;
import hr.tvz.hadzalic.rentacarapp.repository.VoziloJpaRepository;
import hr.tvz.hadzalic.rentacarapp.repository.VoziloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoziloServiceImpl implements VoziloService {

//    private VoziloRepository voziloRepository;
    private VoziloJpaRepository voziloJpaRepository;
    @Override
    public List<VoziloDTO> findAll() {
        return voziloJpaRepository.findAll().stream()
                .map(this::convertVoziloToVoziloDTO)
                .toList();
    }

    @Override
    public List<Vozilo> fetchAll() {
        return voziloJpaRepository.findAll();
    }


    @Override
    public VoziloDTO findVoziloByCode(String code) {
        return voziloJpaRepository.findAll().stream().filter(q->q.getId().toString().equals(code))
                .findFirst().map(this::convertVoziloToVoziloDTO).get();
    }

    @Override
    public Vozilo findVoziloByID(Long id) {
        return voziloJpaRepository.findAll().stream()
                .filter(q->q.getId().equals(id)).findFirst().get();
    }

    @Override
    public Optional<Vozilo> findVoziloByRegistration(String registration) {
        return voziloJpaRepository.findVoziloByRegistration(registration);
    }

    @Override
    public Optional<VoziloDTO> findVoziloByVin(String vin) {
        return voziloJpaRepository.findAll().stream()
                .filter(q -> q.getVin().equals(vin))
                .findFirst()
                .map(this::convertVoziloToVoziloDTO);
    }

    @Override
    public Optional<VoziloDTO> update(Long code, VoziloCommand command) {
        Vozilo target = voziloJpaRepository.getReferenceById(code);

        target.setRegistration(command.getRegistration());
        target.setVin(command.getVin());
        target.setMaxNumberOfPassenger(command.getMaxNumberOfPassenger());
        target.setShifter(command.getShifter());
        target.setAirConditioning(command.getAirConditioning());

        voziloJpaRepository.save(target);
        return Optional.of(convertVoziloToVoziloDTO(target));
    }

    @Override
    public Optional<VoziloDTO> save(VoziloCommand command) {
        if (hasDuplicateRegistrationOrVin(command)) {
            return Optional.empty();
        }

        voziloJpaRepository.save(mapCommandToVozilo(command));
        return Optional.of(mapVoziloToDTO(mapCommandToVozilo(command)));
    }
    @Override
    public void delete(Long vehicleCode) {
        voziloJpaRepository.deleteById(vehicleCode);
    }

    private boolean hasDuplicateRegistrationOrVin(VoziloCommand command) {
        return voziloJpaRepository.existsByRegistration(command.getRegistration()) ||
                voziloJpaRepository.existsByVin(command.getVin());
    }

    private VoziloDTO convertVoziloToVoziloDTO(Vozilo vozilo) {
        return new VoziloDTO(vozilo.getMaxNumberOfPassenger(), vozilo.getShifter(),
                vozilo.getAirConditioning(), vozilo.getNumberOfDoors(), vozilo.getFuelType(),
                vozilo.getMileage() < 5000);
    }

    private Vozilo mapCommandToVozilo(final VoziloCommand command) {
        return new Vozilo(
                99999L,
                command.getRegistration(),
                command.getVin(),
                command.getMaxNumberOfPassenger(),
                command.getShifter(),
                command.getAirConditioning(),
                command.getNumberOfDoors(),
                command.getFuelType(),
                command.getLastServiceDate(),
                command.getNextServiceDate(),
                command.getMileage(),
                command.getReviews()
        );
    }

    private VoziloDTO mapVoziloToDTO(final Vozilo vozilo) {
        return new VoziloDTO(
                vozilo.getMaxNumberOfPassenger(),
                vozilo.getShifter(),
                vozilo.getAirConditioning(),
                vozilo.getNumberOfDoors(),
                vozilo.getFuelType(),
                vozilo.getMileage() < 5000
        );
    }
}
