package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.repository.ReviewJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private ReviewJpaRepository reviewJpaRepository;

    @Override
    public List<Review> getAll() {
        return reviewJpaRepository.findAll();
    }

    @Override
    public Optional<Review> getByID(Long id) {
        return reviewJpaRepository.findAll().stream()
                .filter(q->q.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Review> save(Review review) {
        reviewJpaRepository.save(review);
        return Optional.of(review);
    }

    @Override
    public Optional<Review> update(Long id, Review review) {
        Review target = reviewJpaRepository.findById(id).get();

        target.setTitle(review.getTitle());
        target.setText(review.getText());
        target.setGrade(review.getGrade());
        target.setVozilo(review.getVozilo());

        reviewJpaRepository.save(target);

        return Optional.of(target);
    }

    @Override
    public void delete(Long id) {
        reviewJpaRepository.deleteById(id);
    }

    @Override
    public List<Review> getAllByVoziloRegistration(@PathVariable String voziloRegistration) {
        return reviewJpaRepository.getReviewsByVoziloLike(voziloRegistration);
    }
}
