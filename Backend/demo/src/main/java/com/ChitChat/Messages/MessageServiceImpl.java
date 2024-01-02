package com.ChitChat.Messages;

import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Users.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public void sendMessage(MessageDto messageDto, Authentication authentication){
        Users user = (Users) authentication.getPrincipal();
        int conversationId = messageDto.getConversationId();
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow();
        Messages newMessage = new Messages();
        newMessage.setMessage(messageDto.getMessage());
        newMessage.setConversation(conversation);

        messageRepository.save(newMessage);
    }
}
