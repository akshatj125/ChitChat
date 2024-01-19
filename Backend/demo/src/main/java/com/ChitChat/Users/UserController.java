package com.ChitChat.Users;

import com.ChitChat.Config.UserAuthProvider;
import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.LoginDto.LoginDto;
import com.ChitChat.DTO.LoginDto.LoginMapper;
import com.ChitChat.DTO.SignupDto.SignupDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import com.ChitChat.exceptions.AppException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")

public class UserController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;
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

        if (user.isEmpty() || !userAuthProvider.validateUser(user.get(), loginDto.getUsername(), loginDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = userAuthProvider.createToken(user.get().getUsername());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDetailDto>> showUser() {
        List<Users> users = userService.findAll();
        System.out.println(users);
        return new ResponseEntity<>(UserDetailMapper.mapToUserDto(users), HttpStatus.OK);
    }


    @GetMapping("/profile")
    public ResponseEntity<UserDetailDto> getUserForProfile(Authentication authentication) {
        Users user =(Users) authentication.getPrincipal();
        return new ResponseEntity<>(UserDetailMapper.mapToUserDto(user), HttpStatus.OK);
    }

    @PostMapping("/conversation/{conversationId}")
    @Transactional
    public ResponseEntity<UserDetailDto> addUserToConversation (@PathVariable int conversationId, Authentication authentication)
    {
        Users user = (Users)authentication.getPrincipal();
        Users user1 = userService.addUserToConversation(conversationId, authentication);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserDetailDto userDTO = UserDetailMapper.mapToUserDto(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<ConversationDto>> getConversationsForCurrentUser(Authentication authentication) {
        try {
            List<ConversationDto> conversations = userService.conversationsPerUser(authentication);
            System.out.println(conversations);
            return ResponseEntity.ok(conversations);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDetailDto>> searchUsers(@RequestParam String query) {
        try {
            List<UserDetailDto> searchResults = userService.searchUsers(query);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/profile_picture")
    public ResponseEntity<String> updateProfilePicture(
            Authentication authentication,
            @RequestParam("profilePicture") MultipartFile profilePicture) {
        try {
            String profilePictureId = userService.updateProfilePicture(authentication, profilePicture);
            return new ResponseEntity<>(profilePictureId, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile_picture")
    public ResponseEntity<byte[]> getProfilePicture(Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        String profilePictureId = user.getProfilepicture();

        if (profilePictureId != null) {
            byte[] profilePicture = userService.getProfilePicture(profilePictureId);
            if (profilePicture != null) {
                return new ResponseEntity<>(profilePicture, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}