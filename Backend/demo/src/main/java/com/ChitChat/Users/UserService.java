package com.ChitChat.Services;

import com.ChitChat.Entity.Conversations;
import com.ChitChat.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    Users save(Users user);

    List<Users> findAll();

//    Users findById(int theId);

    Users findByUsername(String username);

    Conversations addUserToGroup(int userId, int groupId);
}
