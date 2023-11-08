package com.ChitChat.Messages;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.DTO.MessageDto.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/saveMessage")
    public Messages saveMessage(@RequestBody Messages message) {
        return messageService.saveMessage(message);
    }

    @GetMapping("/getMessage")
    public List<Messages> findAllMessage() {
        return messageService.findAllMessage();
    }

    @PostMapping("/message/{messageId}/conversation/{conversationId}")
    @Transactional
    public ResponseEntity<MessageDto> addConversationToMessage(@PathVariable int conversationId, @PathVariable int messageId)
    {
        Messages messages = messageService.addConversationToMessage(messageId, conversationId);
        if(messages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MessageDto messageDto = MessageMapper.mapToMessageDto(messages);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}
