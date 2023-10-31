package com.ChitChat.Users;

import com.ChitChat.Config.UserAuthProvider;
import com.ChitChat.DTO.LoginDto.LoginDto;
import com.ChitChat.DTO.LoginDto.LoginMapper;
import com.ChitChat.DTO.SignupDto.SignupDto;
import com.ChitChat.DTO.SignupDto.SignupMapper;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor // Lombok's annotation for generating a constructor with required fields.
public class UserController {

    private final UserService userService; // Autowired UserService for handling user-related operations.
    private final UserAuthProvider userAuthProvider; // Autowired UserAuthProvider for user authentication.

    @PostMapping("/adduser")
    @Transactional
    public ResponseEntity<SignupDto> addUser(@RequestBody Users user){ // Handles a POST request to add a new user.
        Users addedUser = userService.save(user);

        return new ResponseEntity<>(SignupMapper.mapToUserDto(addedUser), HttpStatus.CREATED);// Save the user using the UserService.
    }

    @GetMapping("/users")
    public ResponseEntity<List<LoginDto>> showUser() {
        // Handles a GET request to fetch a list of users.
        List<Users> users = userService.findAll();
        // Retrieve and return a list of all users.

        return new ResponseEntity<>(LoginMapper.mapToUserDto(users), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<LoginDto> getUserById(@PathVariable int userId) {
    // Handles a GET request to fetch a single user.
        Users user = userService.findById(userId).get();
        return new ResponseEntity<>(LoginMapper.mapToUserDto(user), HttpStatus.OK);
    }

    @GetMapping("/alldetail/{userId}")
    public ResponseEntity<UserDetailDto> getUserrById(@PathVariable int userId) {
        // Handles a GET request to fetch a single user.
        Users user = userService.findById(userId).get();
        return new ResponseEntity<>(UserDetailMapper.mapToUserDto(user), HttpStatus.OK);
    }

    @PutMapping("/{userId}/conversation/{conversationId}")
    @Transactional
    public ResponseEntity<UserDetailDto> addUserToConversation (@PathVariable int userId, @PathVariable int conversationId) {
        Users user = userService.addUserToConversation(userId, conversationId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDetailDto userDTO = UserDetailMapper.mapToUserDto(user); // Assuming you have a UserDTO and UserMapper
//        return ResponseEntity.ok(true);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
