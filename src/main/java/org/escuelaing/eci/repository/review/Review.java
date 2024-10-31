package org.escuelaing.eci.repository.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.List;

import org.escuelaing.eci.repository.place.Place;
import org.escuelaing.eci.repository.user.UserDto;

@Document(collection = "Rating_collection")
public class Review {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private List<Review> review;
    private Place place;

    public Review(){

    }
    
    public Review(String id, List<Review> review, Place place) {
        this.id = id;
        this.review = review;
        this.place = place;
    }

    public Review(ReviewDto reviewDto) {
        this.id = null;

        this.place = reviewDto.getPlace();

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Review> getReview() {
        return review;
    }
    public void setReview(List<Review> review) {
        this.review = review;
    }
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public void update(ReviewDto reviewDto) {
        this.place = reviewDto.getPlace();

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
        Review other = (Review) obj;
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
        return "Review{" +
                "id='" + id + '\'' +
                ", review=" + review +
                ", place=" + place +
                '}';
    }

    
    
}

