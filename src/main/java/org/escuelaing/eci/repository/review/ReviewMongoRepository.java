package org.escuelaing.eci.repository.review;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewMongoRepository extends MongoRepository<Review, String> {
    
}
