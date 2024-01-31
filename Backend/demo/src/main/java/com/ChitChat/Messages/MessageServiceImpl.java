package com.ChitChat.Messages;

import com.ChitChat.Config.WebSocketService;
import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.DTO.MessageDto.MessageMapper;
import com.ChitChat.Users.UserRepository;
import com.ChitChat.Users.Users;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final WebSocketService webSocketService;

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
        Users myUser= userRepository.findByUsername(user.getUsername()).orElseThrow(
                ()->new AppException("Could not find user", HttpStatus.NOT_FOUND)
        );
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow();
        Messages newMessage = new Messages();
        newMessage.setMessage(messageDto.getMessage());
        newMessage.setConversation(conversation);
        newMessage.setUser(myUser);
        Messages sentMessage= messageRepository.save(newMessage);

        for (Users users : conversation.getUsers()){
            webSocketService.sendMessage("chat/"+users.getUsername(), MessageMapper.mapToMessageDto(sentMessage));
        }

    }

//    @Override
//    public void updateStatus(MessageDto message, boolean status) {
//        messageRepository.updateStatus(message.getMessageId(), status);
//    }
}
