package com.ChitChat.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
