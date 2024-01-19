package com.ChitChat.DTO.ConversationDto;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.MessageDto.MessageMapper;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;

import java.util.List;

public class ConversationMapper {

    public static ConversationDto mapToConversationDto(Conversations conversation, String username) {

        String name = conversation.getName();
        if (conversation.getUsers().size() == 2){
            Users user2 = conversation.getUsers().stream().filter((user) -> {
                return !user.getUsername().equals(username);
            }).findFirst().orElseThrow();
            name=user2.getUsername();
            System.out.println(name);
        }

        return new ConversationDto(
                conversation.getId(),
                name,
                UserDetailMapper.mapToUserDto(conversation.getUsers()),
                MessageMapper.mapToMessageDto(conversation.getMessages())
        );
    }

    public static List<ConversationDto> mapToConversationDto(List<Conversations> conversation, String username) {
        return conversation.stream().map((conv)->mapToConversationDto(conv,username)).toList();
    }
}

