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

//    private final ConversationRepository conversationRepository;

    private final ConversationService conversationService;

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

    @PostMapping("/conversations/{conversationId}/user/{userId}")
    @Transactional
    public ResponseEntity<ConversationDto> addConversationToUsers(@PathVariable int userId, @PathVariable int conversationId){
        Conversations conversation = conversationService.addConversationToUser(userId, conversationId);
        if (conversation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConversationDto conversationDto = ConversationMapper.mapToConversationDto(conversation);
        return new ResponseEntity<>(conversationDto, HttpStatus.OK);
    }

    @PostMapping("/conversations/{conversationId}/messages/{messageId}")
    @Transactional
    public ResponseEntity<ConversationDto> addMessageToConversation(@PathVariable int conversationId, @PathVariable int messageId)
    {
        Conversations conversation = conversationService.addMessageToConversation(messageId, conversationId);
        if(conversation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConversationDto conversationDto = ConversationMapper.mapToConversationDto(conversation);
        return new ResponseEntity<>(conversationDto, HttpStatus.OK);
    }
}
