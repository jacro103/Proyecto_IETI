package org.escuelaing.eci.repository.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
}
