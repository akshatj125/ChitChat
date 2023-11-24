package com.ChitChat.Conversations;

import com.ChitChat.Users.Users;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService {

    Conversations saveConversations(Conversations conversations);

    List<Conversations> findAllConversation();

    Conversations addConversationToUser(int userId, int conversationId);

    Conversations addMessageToConversation(int messageId, int conversationId);

//    List<Conversations> findConversationByUserid(Authentication authentication);
}
