package com.ChitChat.Messages;

import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Users.Users;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    @Override
    public Messages saveMessage(Messages message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Messages> findAllMessage() {
        return messageRepository.findAll();
    }

    @Override
    public Messages addConversationToMessage(int messageId, int conversationId) {
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow(()->new AppException("Conversation not found", HttpStatus.NOT_FOUND));
        Messages message = messageRepository.findById(messageId).orElseThrow(()->new AppException("Message not found", HttpStatus.NOT_FOUND));
        message.setConversation(conversation);
        messageRepository.save(message);
        return message;
    }
}
