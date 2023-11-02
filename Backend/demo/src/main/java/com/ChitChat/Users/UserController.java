package com.ChitChat.Users;

import com.ChitChat.Config.UserAuthProvider;
import com.ChitChat.DTO.LoginDto.LoginDto;
import com.ChitChat.DTO.LoginDto.LoginMapper;
import com.ChitChat.DTO.SignupDto.SignupDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService; // Autowired UserService for handling user-related operations.
    private final UserAuthProvider userAuthProvider; // Autowired UserAuthProvider for user authentication.
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto signupDto)
    {
        if (userRepository.findByUsername(signupDto.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        Users user = new Users();
        user.setUsername(signupDto.getUsername());
        user.setPassword(signupDto.getPassword());
        user.setEmail(signupDto.getEmail());

        userRepository.save(user);
        return ResponseEntity.ok(userAuthProvider.createToken(user.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        System.out.println("hello");
        Optional<Users> user = userService.findByUsername(loginDto.getUsername());

        if (user.isEmpty() || !userAuthProvider.validateUser(user.get(), loginDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = userAuthProvider.createToken(user.get().getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users")
    public ResponseEntity<List<LoginDto>> showUser() {
        List<Users> users = userService.findAll();
        return new ResponseEntity<>(LoginMapper.mapToUserDto(users), HttpStatus.OK);
    }


    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDetailDto> getUserById(@PathVariable int userId) {
        Users user = userService.findById(userId).get();
        return new ResponseEntity<>(UserDetailMapper.mapToUserDto(user), HttpStatus.OK);
    }

    @PutMapping("{userId}/conversation/{conversationId}")
    @Transactional
    public ResponseEntity<UserDetailDto> addUserToConversation (@PathVariable int userId, @PathVariable int conversationId)
    {
        Users user = userService.addUserToConversation(userId, conversationId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDetailDto userDTO = UserDetailMapper.mapToUserDto(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}