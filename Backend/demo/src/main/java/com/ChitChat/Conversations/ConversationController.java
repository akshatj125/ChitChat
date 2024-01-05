package com.ChitChat.Conversations;

import com.ChitChat.Config.WebSocketService;
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
}
