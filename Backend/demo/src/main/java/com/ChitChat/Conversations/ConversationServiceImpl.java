package com.ChitChat.Conversations;

import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Messages.MessageRepository;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.Users;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    @Override
    public Conversations saveConversations(Conversations conversations) {
        return conversationRepository.save(conversations);
    }

    @Override
    public List<Conversations> findAllConversation() {
        return conversationRepository.findAll();
    }

    @Override
    public Conversations saveConversationWithUsers(Users user1, Users user2,String conversationName) {
        Conversations newConversation = new Conversations();
        newConversation.setUsers(List.of(user1, user2));
        newConversation.setName(conversationName);

        newConversation= conversationRepository.save(newConversation);
        user1.getConversations().add(newConversation);
        user2.getConversations().add(newConversation);
        userRepository.save(user1);
        userRepository.save(user2);
        return newConversation;
    }

}
