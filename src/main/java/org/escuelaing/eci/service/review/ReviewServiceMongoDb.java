package org.escuelaing.eci.service.review;


import org.escuelaing.eci.repository.review.Review;
import org.escuelaing.eci.repository.review.ReviewMongoRepository;
import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.repository.user.UserMongoRepository;
import org.escuelaing.eci.service.user.UsersService;
import org.mindrot.jbcrypt.BCrypt;
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
public class ReviewServiceMongoDb implements ReviewService {

    private final ReviewMongoRepository reviewMongoRepository;

    @Autowired
    public ReviewServiceMongoDb(ReviewMongoRepository reviewMongoRepository) {
        this.reviewMongoRepository = reviewMongoRepository;
    }

    @Override
    public Review save(Review review) {
        return reviewMongoRepository.save(review);
    }

    @Override
    public Optional<Review> findById(String id) {
        return reviewMongoRepository.findById(id);
    }

    @Override
    public List<Review> all() {
        return reviewMongoRepository.findAll();
    }

    @Override
    public Review deleteById(String id) {
        Optional<Review> review = reviewMongoRepository.findById(id);
        if (review.isPresent()) {
            reviewMongoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Review with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public Review update(Review review, String reviewId) {
        return reviewMongoRepository.findById(reviewId)
                .map(existingReview -> {
                    existingReview.setReview(review.getReview());
                    existingReview.setPlace(review.getPlace());
                    return reviewMongoRepository.save(existingReview);
                }).orElse(null);
    }
    
}
