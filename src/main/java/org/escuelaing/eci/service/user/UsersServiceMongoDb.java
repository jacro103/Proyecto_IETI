package org.escuelaing.eci.service.user;

import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.repository.user.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UsersServiceMongoDb(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public User save(User user) {
        return userMongoRepository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return userMongoRepository.findById(id);
    }

    @Override
    public List<User> all() {
        return userMongoRepository.findAll();
    }

    @Override
    public User deleteById(String id) {
        Optional<User> user = userMongoRepository.findById(id);
        if (user.isPresent()) {
            userMongoRepository.deleteById(id);
        } else {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        return null;
    }

    @Override
    public User update(User user, String userId) {
        return userMongoRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setLastName(user.getLastName());
                    existingUser.setEmail(user.getEmail());
                    return userMongoRepository.save(existingUser);
                }).orElse(null);
    }
}
