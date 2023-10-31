package com.ChitChat.DTO.ConversationDto;

import com.ChitChat.Conversations.Conversations;

import java.util.List;

public class ConversationMapper {

    public static ConversationDto mapToConversationDto(Conversations conversation) {
        return new ConversationDto(
                conversation.getName(),
                conversation.getParticipants()
        );
    }

    public static List<ConversationDto> mapToConversationDto(List<Conversations> conversation){
        return conversation.stream().map(ConversationMapper::mapToConversationDto).toList();
    }
}
