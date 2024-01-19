package com.ChitChat.Conversations;

import com.ChitChat.Config.WebSocketService;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.UserService;
import com.ChitChat.Users.Users;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final UserService userService;

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

    @PostMapping("/createConversations")
    @Transactional
    public ResponseEntity<ConversationDto> findOrCreateConversation(@RequestBody String name2, Authentication authentication) {

        String name1 = ((Users) authentication.getPrincipal()).getUsername();

        Users currentUser = userRepository.findByUsername(name1).orElseThrow(()-> new AppException("User not found",HttpStatus.NOT_FOUND));
        Users otherUser = userRepository.findByUsername(name2).orElseThrow(()-> new AppException("User not found",HttpStatus.NOT_FOUND));
        if(name1.compareTo(name2)>0){
            String temp = name1;
            name1 = name2;
            name2 = temp;
        }

        String conversationName =name1+"-"+name2;
        ConversationDto conversationDto;
        Optional<Conversations> conversation = conversationRepository.findByName(conversationName);
        if(conversation.isPresent()){
            conversationDto = ConversationMapper.mapToConversationDto(conversation.get(), currentUser.getUsername());
        }
        else{
            Conversations newConversation = conversationService.saveConversationWithUsers(currentUser, otherUser,conversationName);

            conversationDto = ConversationMapper.mapToConversationDto(newConversation, currentUser.getUsername());
        }
        return ResponseEntity.ok(conversationDto);
    }
    
}
