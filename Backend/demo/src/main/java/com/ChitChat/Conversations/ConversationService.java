package com.ChitChat.Conversations;

import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService {

    Conversations saveConversations(Conversations conversations);

    List<Conversations> findAllConversation();

}
