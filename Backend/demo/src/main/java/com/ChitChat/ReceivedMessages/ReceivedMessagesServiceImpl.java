package com.ChitChat.ReceivedMessages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivedMessagesServiceImpl implements ReceivedMessagesService{

    private final ReceivedMessagesRepository receivedMessagesRepository;

    @Autowired
    public ReceivedMessagesServiceImpl(ReceivedMessagesRepository receivedMessagesRepository) {
        this.receivedMessagesRepository = receivedMessagesRepository;
    }

    @Override
    public ReceivedMessages save(ReceivedMessages receivedMessages) {
        return receivedMessagesRepository.save(receivedMessages);
    }

    @Override
    public List<ReceivedMessages> findAll() {
        return receivedMessagesRepository.findAll();
    }
}
