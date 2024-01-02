package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.exceptions.AppException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
    public Users addUserToConversation(int conversationId, Authentication authentication) {
//        System.out.println("hello");
        Users user = (Users) authentication.getPrincipal();
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow(()->new AppException("Conversation not found", HttpStatus.NOT_FOUND));
        user.getConversations().add(conversation);
        userRepository.save(user);
        return user;
    }

    @Override
    public void removeUser(int userId) {
        Users user = userRepository.findById(userId).orElseThrow(()->new AppException("Could not find user", HttpStatus.NOT_FOUND));
        userRepository.deleteById(userId);
    }

    @Override
    public List<Conversations> conversationsPerUser(Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        return user.getConversations();
    }
}