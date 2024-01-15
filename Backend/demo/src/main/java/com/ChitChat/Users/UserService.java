package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Users save(Users user);

    List<Users> findAll();

    Optional<Users> findById(int theId);

    Optional<Users> findByUsername(String username);

    Users addUserToConversation(int conversationId, Authentication authentication);

    void removeUser(int userId);

    List<ConversationDto> conversationsPerUser(Authentication authentication);

    List<UserDetailDto> searchUsers(String query);

}
