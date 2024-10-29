package org.escuelaing.eci.component;


import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.repository.user.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.List;


@Document(collection = "LoginUser_collection")
public class LoginUser implements Serializable{

    @Autowired
    private final UserMongoRepository userMongoRepository;
    @Autowired
    UserMongoRepository userService;

    private String name;
    private String password;

    private List<User> userList;

    public LoginUser(UserMongoRepository userRepository){
        this.userMongoRepository=userRepository;
    }

    public String getName(){
        return this.name;
    }


    public String getPassword(){
        return this.password;
    }
    public void setName(String name){
        this.name = name;
    }


    public void setPassword(String password){
        this.password = password;
    }

 
}
