package org.escuelaing.eci.service.user;

import org.escuelaing.eci.repository.user.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    User save(User user);

    Optional<User> findById(String id);

    List<User> all();

    User deleteById(String id);

    User update(User user, String userId);

    
}

