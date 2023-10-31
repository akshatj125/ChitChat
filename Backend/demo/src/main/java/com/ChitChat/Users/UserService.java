package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Users save(Users user);

    List<Users> findAll();

    Optional<Users> findById(int theId);

    Optional<Users> findByUsername(String username);

    Users addUserToConversation(int userId, int conversationId);
}
