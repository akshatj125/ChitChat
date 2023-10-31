package com.ChitChat.Conversations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;

    @Autowired
    public ConversationServiceImpl(ConversationRepository theConversationRepository) {
        this.conversationRepository = theConversationRepository;
    }

    @Override
    public Conversations saveConversations(Conversations conversations) {
        return conversationRepository.save(conversations);
    }

    @Override
    public List<Conversations> findAllConversation() {
        return conversationRepository.findAll();
    }
}
