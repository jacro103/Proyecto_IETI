package org.escuelaing.eci.service.rating;

import org.escuelaing.eci.repository.rating.Rating;
import org.escuelaing.eci.repository.rating.RatingMongoRepository;
import org.escuelaing.eci.repository.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class RatingMongoService implements RatingService {

    private final RatingMongoRepository ratingMongoRepository;

    @Autowired
    public RatingMongoService(RatingMongoRepository ratingMongoRepository) {
        this.ratingMongoRepository = ratingMongoRepository;
    }
    
    @Override
    public Rating save(Rating rating) {
        return ratingMongoRepository.save(rating);
    }

    @Override
    public Optional<Rating> findById(String id) {
        return ratingMongoRepository.findById(id);
    }


    @Override
    public List<Rating> all() {
        return ratingMongoRepository.findAll();
    }

    @Override
    public Rating deleteById(String id) {
        Optional<Rating> rating = ratingMongoRepository.findById(id);
        if (rating.isPresent()) {
            ratingMongoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Rating with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public Rating update(Rating review, String rating) {
        return ratingMongoRepository.findById(rating)
                .map(existingRating -> {
                    existingRating.setReview(review.getReview());
                    existingRating.setPlace(review.getPlace());
                    return ratingMongoRepository.save(existingRating);
                }).orElse(null);
    }    

}

