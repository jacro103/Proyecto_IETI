package org.escuelaing.eci.repository.preference;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceMongoRepository  extends MongoRepository<Preference, String>{
    
}
