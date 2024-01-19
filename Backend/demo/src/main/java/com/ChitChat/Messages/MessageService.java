package com.ChitChat.Messages;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MessageService {

    Messages saveMessage(Messages message);

    List<Messages> findAllMessage();

    void sendMessage(MessageDto messageDto, Authentication authentication);

    void updateStatus(MessageDto message, boolean status);

}
