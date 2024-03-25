package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Poslovnica;
import hr.tvz.hadzalic.rentacarapp.entity.PoslovnicaCommand;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;
import hr.tvz.hadzalic.rentacarapp.repository.PoslovnicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PoslovnicaServiceImpl implements PoslovnicaService{

    private PoslovnicaRepository poslovnicaRepository;

    @Override
    public List<PoslovnicaDTO> findAll() {
        return poslovnicaRepository.findAll().stream()
                .map(this::convertPoslovnicaToPoslovnicaDTO)
                .toList();
    }

    @Override
    public Optional<PoslovnicaDTO> findPoslovnicaByID(String id) {
        return poslovnicaRepository.findAll().stream()
                .filter(q->q.getId().toString().equals(id))
                .findFirst().map(this::convertPoslovnicaToPoslovnicaDTO);
    }

    @Override
    public Optional<PoslovnicaDTO> save(PoslovnicaCommand command) {
        return poslovnicaRepository.save(mapCommandToPoslovnica(command)).map(this::mapPoslovnicaToDTO);

    }

    @Override
    public void delete(Long id) {
        poslovnicaRepository.delete(id);
    }

    private PoslovnicaDTO convertPoslovnicaToPoslovnicaDTO(Poslovnica poslovnica) {
        return new PoslovnicaDTO(
                poslovnica.getName(),
                poslovnica.getAdress(),
                poslovnica.getNumberOfEmployees(),
                poslovnica.getDate());
    }

    private Poslovnica mapCommandToPoslovnica(final PoslovnicaCommand command) {
        return new Poslovnica(
                command.getId(),
                command.getName(),
                command.getAdress(),
                command.getNumberOfEmployees(),
                command.getDate()
        );
    }

    private PoslovnicaDTO mapPoslovnicaToDTO(final Poslovnica poslovnica) {
        return new PoslovnicaDTO(
                poslovnica.getName(),
                poslovnica.getAdress(),
                poslovnica.getNumberOfEmployees(),
                poslovnica.getDate()
        );
    }

}
