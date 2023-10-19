package com.ChitChat.Controllers;

import com.ChitChat.Config.UserAuthProvider;
import com.ChitChat.Entity.User;
import com.ChitChat.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor // Lombok's annotation for generating a constructor with required fields.
public class Controller {

    private final UserService userService; // Autowired UserService for handling user-related operations.
    private final UserAuthProvider userAuthProvider; // Autowired UserAuthProvider for user authentication.

    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        // Handles a POST request to add a new user.
        User users = userService.save(user); // Save the user using the UserService.
        String token = userAuthProvider.createToken(users.getUsername()); // Create an authentication token.
        return ResponseEntity.ok(token); // Return the authentication token as a response.
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User theUser) {
        // Handles a PUT request to update a user.
        return userService.save(theUser); // Save the updated user using the UserService.
    }

    @GetMapping("/users")
    public List<User> showUser(User user) {
        // Handles a GET request to fetch a list of users.
        return userService.findAll(); // Retrieve and return a list of all users.
    }

    @GetMapping("/users/{theId}")
    public User getUser(@PathVariable int theId) {
        // Handles a GET request to fetch a specific user by their ID.
        User user = userService.findById(theId); // Retrieve the user with the specified ID.
        if (user == null) {
            throw new RuntimeException("User not found"); // Throw an exception if the user is not found.
        }
        return user; // Return the found user.
    }

    @GetMapping("/hello")
    public String hello() {
        // Handles a GET request to the "/hello" endpoint.
        return "Hello"; // Simply returns the "Hello" string as a response.
    }
}
