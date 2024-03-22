package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.entity.VoziloCommand;
import hr.tvz.hadzalic.rentacarapp.service.VoziloService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vozilo")
@AllArgsConstructor
@Slf4j
public class VoziloController {

    private VoziloService voziloService;

    @GetMapping("/all")
    public List<VoziloDTO> getAll() {
        log.info("Called method findAll()");
        return voziloService.findAll();
    }

    @GetMapping("/{code}")
    public VoziloDTO getVoziloByCode(@PathVariable String code) {
        log.info("Called method getVoziloByCode()");
        return voziloService.findVoziloByCode(code);
    }

    @PostMapping
    public ResponseEntity<VoziloDTO> save(@Valid @RequestBody final VoziloCommand command) {
        return voziloService.save(command)
                .map(
                        voziloDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(voziloDTO)
                )
                .orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void delete(@PathVariable Long code) {
        voziloService.delete(code);
    }
}
