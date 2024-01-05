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

    @Override
    public Conversations saveConversations(Conversations conversations) {
        return conversationRepository.save(conversations);
    }

    @Override
    public List<Conversations> findAllConversation() {
        return conversationRepository.findAll();
    }

}
