package org.escuelaing.eci.Controller;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.escuelaing.eci.repository.review.Review;
import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.service.review.ReviewService;
import org.escuelaing.eci.service.user.UsersService;
import org.escuelaing.eci.service.user.UsersServiceMongoDb;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping()
    public ArrayList<Review> getReview() {
        return (ArrayList<Review>) this.reviewService.all();
    }

    @PostMapping()
    public Review saveReview(@RequestBody Review review) {
        return this.reviewService.save(review);
    }

    @GetMapping("/getReview/{id}")
    public Optional<Review> getReview(@PathVariable String id) {
        return this.reviewService.findById(id);
    }

    @PostMapping("/create")
    public Review createReview(@RequestBody Review review) {
        System.out.println("Received request to create Review: " + review);
        return this.reviewService.save(review);
    }

    @PostMapping("/update")
    public Review updateUser(@RequestBody Review review, String reviewId) {
        System.out.println("Received request to create user: " + review);
        return this.reviewService.update(review, reviewId);
    }

    @GetMapping("/delete/{id}")
    public Review deleteReview(@PathVariable String id) {
        return this.reviewService.deleteById(id);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }
}
