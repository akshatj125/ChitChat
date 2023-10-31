package com.ChitChat.Conversations;

import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConversationController {

    private UserRepository userRepository;

    private ConversationRepository conversationRepository;

    private ConversationService conversationService;

    @Autowired
    public ConversationController(ConversationService theConversationService) {
        this.conversationService = theConversationService;
    }

    @PostMapping("/conversations")
    @Transactional
    public Conversations saveConversation(@RequestBody Conversations conversations) {
        return conversationService.saveConversations(conversations);
    }

    @GetMapping("/viewConversations")
    public ResponseEntity<List<ConversationDto>> viewConversations(){
        List<Conversations> conversations = conversationService.findAllConversation();

        return new ResponseEntity<>(ConversationMapper.mapToConversationDto(conversations), HttpStatus.OK);
    }
}
