package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.dto.VoziloDTO;
import hr.tvz.hadzalic.rentacarapp.service.VoziloService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
