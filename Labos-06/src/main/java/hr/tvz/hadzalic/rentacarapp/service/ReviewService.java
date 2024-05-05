package hr.tvz.hadzalic.rentacarapp.service;

import hr.tvz.hadzalic.rentacarapp.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAll();
    List<Review> getAllByVoziloRegistration(String voziloRegistration);
}
