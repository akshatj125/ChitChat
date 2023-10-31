package com.ChitChat.ReceivedMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceivedMessagesController {

    private final ReceivedMessagesService receivedMessagesService;

    @Autowired
    public ReceivedMessagesController(ReceivedMessagesService theReceivedMessagesService) {
        this.receivedMessagesService = theReceivedMessagesService;
    }

    @PostMapping("/saveReceivedMessage")
    public ReceivedMessages save(ReceivedMessages receivedMessages) {
        return receivedMessagesService.save(receivedMessages);
    }

    @GetMapping("/viewReceivedMessage")
    public List<ReceivedMessages> findAll() {
        return receivedMessagesService.findAll();
    }
}
