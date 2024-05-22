package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.PoslovnicaCommand;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;
import hr.tvz.hadzalic.rentacarapp.service.PoslovnicaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poslovnica")
@AllArgsConstructor
@Slf4j
public class PoslovnicaController {

    private PoslovnicaService poslovnicaService;

    @GetMapping
    public List<PoslovnicaDTO> getAll() {
        log.info("Called method findAll()");
        return poslovnicaService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<PoslovnicaDTO> findPoslovnicaById(@PathVariable String id) {
        log.info("Called method findPoslovnicaById()");
        return poslovnicaService.findPoslovnicaByID(id);
    }

    @PostMapping
    public ResponseEntity<PoslovnicaDTO> save(@Valid @RequestBody final PoslovnicaCommand command) {
        log.info("Called method save()");
        return poslovnicaService.save(command)
                .map(
                        poslovnicaDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(poslovnicaDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Called method delete()");
        poslovnicaService.delete(id);
    }

}
