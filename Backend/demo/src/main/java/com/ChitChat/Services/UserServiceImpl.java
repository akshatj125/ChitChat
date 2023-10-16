package com.ChitChat.Services;

import com.ChitChat.Entity.User;
import com.ChitChat.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }
}
