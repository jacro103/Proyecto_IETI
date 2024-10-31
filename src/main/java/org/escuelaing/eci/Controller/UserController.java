package org.escuelaing.eci.Controller;


import java.util.ArrayList;

import java.util.Optional;

import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.service.user.UsersService;
import org.escuelaing.eci.service.user.UsersServiceMongoDb;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
    private final UsersService userService;
    private  UsersServiceMongoDb usersServiceMongoDb;

    public UserController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ArrayList<User> getUsers() {
        return (ArrayList<User>) this.userService.all();
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return this.userService.save(user);
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUser(@PathVariable String id) {
        return this.userService.findById(id);
    }



    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        System.out.println("Received request to create user: " + user);
        return this.userService.save(user);
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody User user, String userId) {
        System.out.println("Received request to create user: " + user);
        return this.userService.update(user, userId);
    }

    @GetMapping("/delete/{id}")
    public User deleteUser(@PathVariable String id) {
        return this.userService.deleteById(id);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }

    

}
