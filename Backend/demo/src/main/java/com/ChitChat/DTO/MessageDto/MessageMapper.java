package com.ChitChat.DTO.MessageDto;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.Messages.Messages;

import java.util.List;

public class MessageMapper {

    public static MessageDto mapToMessageDto(Messages messages) {
        return new MessageDto(
                messages.getId(),
                messages.getMessage(),
                messages.getConversation().getId(),
                messages.getUser().getUsername(),
                messages.getTimestamp(),
                messages.isSeen()
        );
    }



    public static List<MessageDto> mapToMessageDto(List<Messages> messages){
        if(messages==null){
            return null;
        }
        return messages.stream().map(MessageMapper::mapToMessageDto).toList();
    }
}
