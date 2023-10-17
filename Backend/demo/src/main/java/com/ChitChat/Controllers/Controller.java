package com.ChitChat.Controllers;

import com.ChitChat.Entity.User;
import com.ChitChat.Services.UserService;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    private UserService userService;

    public Controller(UserService theUserService){
        userService = theUserService;
    }

    @PostMapping("/adduser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        System.out.println(user);
        User users = userService.save(user);
        System.out.println(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser){
        return userService.save(theUser);
    }

    @GetMapping("/users")
    public List<User> showUser(User user){
        return userService.findAll();
    }

    @GetMapping("/users/{theId}")
    public User getUser(@PathVariable int theId){
        User user = userService.findById(theId);
        if(user == null){
            throw new RuntimeException("User not found");
        }
        return user;
    }
}
