package com.ChitChat.Conversations;

import com.ChitChat.Messages.MessageRepository;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.Users;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    @Override
    public Conversations saveConversations(Conversations conversations) {
        return conversationRepository.save(conversations);
    }

    @Override
    public List<Conversations> findAllConversation() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversations addConversationToUser(int userId, int conversationId) {
        Users user = userRepository.findById(userId).orElseThrow(()->new AppException("User not found", HttpStatus.NOT_FOUND));
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow(()->new AppException("Conversation not found", HttpStatus.NOT_FOUND));
        conversation.getUsers().add(user);
        conversationRepository.save(conversation);
        return conversation;
    }

    @Override
    public Conversations addMessageToConversation(int messageId, int conversationId) {
        Messages message = messageRepository.findById(messageId).orElseThrow(()->new AppException("Message not found", HttpStatus.NOT_FOUND));
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow(()->new AppException("Conversation not found", HttpStatus.NOT_FOUND));
        conversation.getMessages().add(message);
        conversationRepository.save(conversation);
        return conversation;
    }
}
