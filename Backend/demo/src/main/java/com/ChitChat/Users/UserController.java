package com.ChitChat.Controllers;

import com.ChitChat.Config.UserAuthProvider;
import com.ChitChat.DTO.ConversationDto;
import com.ChitChat.DTO.UserDetailDto;
import com.ChitChat.Entity.Conversations;
import com.ChitChat.Entity.Users;
import com.ChitChat.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Lombok's annotation for generating a constructor with required fields.
public class UserController {

    private final UserService userService; // Autowired UserService for handling user-related operations.
    private final UserAuthProvider userAuthProvider; // Autowired UserAuthProvider for user authentication.

    @PostMapping("/adduser")
//    public ResponseEntity<String> addUser(@RequestBody Users user) {
//        // Handles a POST request to add a new user.
//        Users users = userService.save(user); // Save the user using the UserService.
//        String token = userAuthProvider.createToken(users.getUsername()); // Create an authentication token.
//        return ResponseEntity.ok(token); // Return the authentication token as a response.
//    }

    public Users addUser(@RequestBody Users user){
        // Handles a POST request to add a new user.
        return userService.save(user); // Save the user using the UserService.
    }

    @PutMapping("/users")
    public Users updateUser(@RequestBody Users theUser) {
        // Handles a PUT request to update a user.
        return userService.save(theUser); // Save the updated user using the UserService.
    }

    @GetMapping("/users")
    public List<Users> showUser(Users user) {
        // Handles a GET request to fetch a list of users.
        return userService.findAll(); // Retrieve and return a list of all users.
    }

//    @GetMapping("/users/{theId}")
//    public Users getUser(@PathVariable int theId) {
//        // Handles a GET request to fetch a specific user by their ID.
//        Users user = userService.findById(theId); // Retrieve the user with the specified ID.
//        if (user == null) {
//            throw new RuntimeException("User not found"); // Throw an exception if the user is not found.
//        }
//        return user; // Return the found user.
//    }

//    @PutMapping("/{userId}/group/{groupId}")
//    public ResponseEntity<ConversationDto> addUserToGroup(@PathVariable int userId, @PathVariable int groupId) {
//        Groups group = userService.addUserToGroup(userId, groupId);
//        return ResponseEntity.ok(Conversations.c(group));
//    }
}
