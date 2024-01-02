package com.ChitChat.DTO.ConversationDto;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;

import java.util.List;

public class ConversationMapper {

    public static ConversationDto mapToConversationDto(Conversations conversation, String username) {

        String name = conversation.getName();
        if (conversation.getUsers().size() == 2){
            Users user2 = conversation.getUsers().stream().filter((user1) -> {
                return !user1.getUsername().equals(username);
            }).findFirst().orElseThrow();
            name=user2.getUsername();
        }

        return new ConversationDto(
                conversation.getId(),
                name,
                conversation.getUsers(),
                conversation.getMessages()
        );
    }

    public static List<ConversationDto> mapToConversationDto(List<Conversations> conversation, String username) {
        return conversation.stream().map((conv)->mapToConversationDto(conv,username)).toList();
    }
}

