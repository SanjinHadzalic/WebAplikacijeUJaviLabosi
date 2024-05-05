package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/{voziloRegistration}")
    public List<Review> findReviewByVoziloRegistration(@PathVariable String voziloRegistration) {
        log.info("Called method findReviewByVoziloRegistration() in ReviewController");
        return reviewService.getAllByVoziloRegistration(voziloRegistration);
    }
}
