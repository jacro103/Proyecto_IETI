package org.escuelaing.eci.repository.rating;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingMongoRepository extends MongoRepository<Rating, String> {
    // Puedes agregar métodos personalizados de consulta aquí, si es necesario
}

