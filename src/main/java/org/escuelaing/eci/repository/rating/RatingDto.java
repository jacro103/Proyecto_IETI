package org.escuelaing.eci.repository.rating;

import java.util.List;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.repository.review.Review;

public class RatingDto {

    private Review review;
    private Place place;



    public RatingDto() {
        this.review = null;
        this.place = null;
    }

    public RatingDto( Review review, Place place) {
        this.review = review;
        this.place = place;
    }

    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }



}
