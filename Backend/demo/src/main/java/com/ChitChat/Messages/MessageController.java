package com.ChitChat.Messages;

import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.DTO.MessageDto.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
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


    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody MessageDto messageDto, Authentication authentication) {
        System.out.println("Send Message");
        try {
            messageService.sendMessage(messageDto, authentication);
            System.out.println("Message sent");
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending message");
        }
    }

//    @PutMapping("/update")
//    public ResponseEntity<MessageDto> updateSeenStatus(@RequestBody MessageDto message, @RequestParam boolean status){
//        try{
//            messageService.updateStatus(message,status);
//            return ResponseEntity.ok(message);
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
//        }
//    }
}