package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.repository.ReviewJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private ReviewJpaRepository reviewJpaRepository;

    @Override
    public List<Review> getAll() {
        return reviewJpaRepository.findAll();
    }

    @Override
    public List<Review> getAllByVoziloRegistration(@PathVariable String voziloRegistration) {
        return reviewJpaRepository.getReviewsByVoziloLike(voziloRegistration);
    }
}
