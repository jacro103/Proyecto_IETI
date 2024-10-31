package org.escuelaing.eci.service.rating;

import java.util.List;

import org.escuelaing.eci.repository.rating.Rating;




public interface RatingService {
    
    Rating save(Rating rating);

    java.util.Optional<Rating> findById(String id);

    List<Rating> all();

    Rating deleteById(String id);

    Rating update(Rating rating, String ratingId);
}
