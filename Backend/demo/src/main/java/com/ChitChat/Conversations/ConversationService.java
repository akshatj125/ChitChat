package com.ChitChat.Conversations;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConversationService {

    Conversations saveConversations(Conversations conversations);

    List<Conversations> findAllConversation();

}