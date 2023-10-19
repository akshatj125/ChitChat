package com.ChitChat.Services;

import com.ChitChat.Entity.User;
import com.ChitChat.UserRepository;
import com.ChitChat.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }

    @Override
    public User save(User theUser) {
        Optional<User> user = userRepository.findByUsername(theUser.getUsername());
        if(user.isPresent()){
            throw new AppException("User already exists", HttpStatus.CONFLICT);
        }
        return userRepository.save(theUser);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User theUser = null;
        if(result.isPresent()){
            theUser = result.get();
        }
        else{
            throw new RuntimeException();
        }

        return theUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }
}