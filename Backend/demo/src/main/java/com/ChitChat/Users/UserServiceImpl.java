package com.ChitChat.Services;

import com.ChitChat.Entity.Conversations;
import com.ChitChat.Entity.Users;
import com.ChitChat.Repository.ConversationRepository;
import com.ChitChat.Repository.UserRepository;
import com.ChitChat.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Override
    public Users save(Users theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findByUsername(String username) {
        return null;
    }

    @Override
    public Conversations addUserToGroup(int userId, int groupId) {

        Users user = userRepository.findById(userId).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        Conversations conversations = conversationRepository.findById(groupId).orElseThrow(() -> new AppException("Group not found", HttpStatus.NOT_FOUND));

//        Set<Users> users = group.getGroupUsers();
//        users.add(user);
//        group.setGroupUsers(users);
//        return groupRepository.save(group);

        List<Conversations> groups = user.getConversations();
        groups.add(conversations);
        user.setConversations(groups);
//        System.out.println(userRepository.save(user));
//        user=userRepository.findById(userId).get();
//        System.out.println(user);
        return conversationRepository.findById(conversations.getId()).get();
    }
}