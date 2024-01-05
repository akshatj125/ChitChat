package com.ChitChat.DTO.ConversationDto;

import com.ChitChat.DTO.MessageDto.MessageDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {

    private Integer id;

    private String name;

    private List<UserDetailDto> participants;

    private List<MessageDto> messages;
}
