package org.escuelaing.eci.repository.review;

import org.escuelaing.eci.repository.place.Place;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;

public class ReviewDto {
    

    private Review review;

    
    private Place place;
    
    
    public ReviewDto() {
        this.review = null;
        this.place = null;
    }

    public ReviewDto(Review review, Place place) {
        this.review = review;
        this.place = place;
    }


    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
  
}
