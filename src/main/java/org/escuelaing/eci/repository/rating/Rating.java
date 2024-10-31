package org.escuelaing.eci.repository.rating;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.escuelaing.eci.repository.place.Place;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.escuelaing.eci.repository.review.Review;
import org.escuelaing.eci.repository.user.UserDto;


public class Rating {
    private String id;
    private Review review;
    private Place place;

    // Constructor
    public Rating(String id, Review review, Place place) {
        this.id = id;
        this.review = review;
        this.place = place;
    }

    public Rating(RatingDto ratingDto) {
        this.id = "";
        this.review = ratingDto.getReview();
        this.place = ratingDto.getPlace();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Review  getReview() {
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

    public void update(RatingDto ratingDto) {
        this.review = ratingDto.getReview();
        this.place = ratingDto.getPlace();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((review == null) ? 0 : review.hashCode());
        result = prime * result + ((place == null) ? 0 : place.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rating other = (Rating) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (review == null) {
            if (other.review != null)
                return false;
        } else if (!review.equals(other.review))
            return false;
        if (place == null) {
            if (other.place != null)
                return false;
        } else if (!place.equals(other.place))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", review=" + review +
                ", place=" + place +
                '}';
    }



    
}

