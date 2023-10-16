package com.ChitChat.Controllers;

import com.ChitChat.Entity.User;
import com.ChitChat.Services.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {

    private UserService userService;

    public Controller(UserService theUserService){
        userService = theUserService;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.save(user);
    }
}
