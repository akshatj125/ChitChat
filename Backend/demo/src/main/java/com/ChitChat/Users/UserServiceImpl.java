package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Conversations.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(int theId) {
        return userRepository.findById(theId);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users addUserToConversation(int userId, int conversationId) {
        System.out.println("hello");
        Users user = userRepository.findById(userId).get();
        Conversations conversation = conversationRepository.findById(conversationId).get();
        user.getConversations().add(conversation);
        userRepository.save(user);
        return user;
    }
}