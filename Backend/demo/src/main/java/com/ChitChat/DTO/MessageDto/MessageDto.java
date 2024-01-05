package com.ChitChat.DTO.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String message;

    private int conversationId;

    private String senderUsername;

    private Date sentAt;
}
