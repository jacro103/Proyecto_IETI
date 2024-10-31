package org.escuelaing.eci.service.review;


import java.util.List;
import java.util.Optional;

import org.escuelaing.eci.repository.review.Review;




public interface ReviewService {

    Review save(Review review);

    Optional<Review> findById(String id);

    List<Review> all();

    Review deleteById(String id);

    Review update(Review review, String ReviewId);
}
