package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping
    public List<Review> getAll() {
        log.info("Called method getAll() in ReviewController");
        return reviewService.getAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Review> getReviewById(@PathVariable Long id) {
        log.info("Called method getReviewById() in ReviewController");
        return reviewService.getByID(id);
    }

    @GetMapping("/{voziloRegistration}")
    public List<Review> findReviewByVoziloRegistration(@PathVariable String voziloRegistration) {
        log.info("Called method findReviewByVoziloRegistration() in ReviewController");
        return reviewService.getAllByVoziloRegistration(voziloRegistration);
    }

    @PostMapping
    public ResponseEntity<Review> save(@RequestBody final Review review) {
        return reviewService.save(review).map(
                vozilo -> ResponseEntity
                        .status(HttpStatus.CREATED).body(vozilo))
                .orElseGet(
                        ()->ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> update(@PathVariable Long id, @RequestBody Review review){
        return reviewService.update(id, review)
                .map(ResponseEntity::ok)
                .orElseGet(
                        ()->ResponseEntity.notFound().build()
                );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }
}
