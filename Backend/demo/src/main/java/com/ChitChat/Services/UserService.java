package com.ChitChat.Services;

import com.ChitChat.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User save(User user);

    List<User> findAll();

    User findById(int theId);
}
