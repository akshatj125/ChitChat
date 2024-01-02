package com.ChitChat.DTO.MessageDto;

import com.ChitChat.Conversations.Conversations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String message;

    private int conversationId;

}
