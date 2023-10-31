package com.ChitChat.DTO.ConversationDto;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Users.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {

    private String name;

    private List<Users> participants;

}