package com.ChitChat.DTO.UserDetailDto;

import com.ChitChat.Conversations.Conversations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

    private String username;
    private String email;
    private String profilepicture;
    private LocalDateTime created_at;
    private List<Conversations> conversations;

}
