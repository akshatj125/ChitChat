package com.ChitChat.Messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Messages saveMessage(Messages message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Messages> findAllMessage() {
        return messageRepository.findAll();
    }
}
