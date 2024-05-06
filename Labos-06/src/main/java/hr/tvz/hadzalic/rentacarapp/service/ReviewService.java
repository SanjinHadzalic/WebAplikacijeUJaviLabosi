package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<Review> getAll();
    Optional<Review> getByID(Long id);
    Optional<Review> save(Review review);
    Optional<Review> update(Long id, Review review);
    void delete(Long id);
    List<Review> getAllByVoziloRegistration(String voziloRegistration);
}
