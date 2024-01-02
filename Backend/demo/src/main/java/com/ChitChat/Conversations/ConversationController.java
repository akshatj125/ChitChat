package com.ChitChat.Conversations;

import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.UserService;
import com.ChitChat.Users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ConversationController {

    private final ConversationService conversationService;

    private final UserService userService;

    private final ConversationRepository conversationRepository;

    private final UserRepository userRepository;

    @PostMapping("/conversations")
    @Transactional
    public Conversations saveConversation(@RequestBody Conversations conversations) {
        return conversationService.saveConversations(conversations);
    }

    @GetMapping("/viewConversations")
    public ResponseEntity<List<ConversationDto>> viewConversations(Authentication authentication){
        Users user = (Users) authentication.getPrincipal();
        List<Conversations> conversations = conversationService.findAllConversation();
        return new ResponseEntity<>(ConversationMapper.mapToConversationDto(conversations, user.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/conversations")
    public ResponseEntity<List<ConversationDto>> conversationsOfUser(Authentication authentication){
        Users user =(Users) authentication.getPrincipal();

        List<Conversations> conversation = conversationService.findAllConversation();

        return new ResponseEntity<>(ConversationMapper.mapToConversationDto(conversation, user.getUsername()), HttpStatus.OK);
    }

//    @PostMapping("/sendMessage")
//    @Transactional
//    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto, Authentication authentication) {
//        try {
//            conversationService.sendMessage(messageDto, authentication);
//            return ResponseEntity.ok("Message sent successfully");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
//        }
//    }
}
