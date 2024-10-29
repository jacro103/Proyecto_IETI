package org.escuelaing.eci.service.user;

import org.escuelaing.eci.config.KeyPairConfig;
import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.repository.user.UserMongoRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceMongoDb implements UsersService {

    private KeyPairConfig keyPair;

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

    @Transactional
    public void register(User user){
        if(userMongoRepository.findByLogin(user.getEmail()).isPresent()) throw new IllegalStateException("login taken");
        else if (userMongoRepository.findByEmail(user.getEmail()).isPresent()) throw new IllegalStateException("email taken");
        else {
        user.setPasswordHash(BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt(11)));
        userMongoRepository.save(user);
        }
    }

    @Transactional
    public String login(User user) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        if(!userMongoRepository.findByLogin(user.getEmail()).isPresent()) throw new IllegalStateException("login doesn't exist");
        else if (!BCrypt.checkpw(user.getPasswordHash(),userMongoRepository.findByLogin(user.getEmail()).orElseThrow(() -> new IllegalStateException(
                "mailStory with id " + user.getEmail() + " does not exists")).getPasswordHash()
                )) throw new IllegalStateException("password os incorrect");
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .claim("email", user.getEmail())
                .issuer(user.getEmail())
                .build();

        JWSSigner signer = new RSASSASigner(keyPair.getPrivate());

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);

        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}
