package com.ChitChat.DTO.SignupDto;

import com.ChitChat.Users.Users;

import java.util.List;

public class SignupMapper {

    public static SignupDto mapToUserDto(Users user) {
        return new SignupDto(
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static List<SignupDto> mapToUserDto(List<Users> users){
        return users.stream().map(SignupMapper::mapToUserDto).toList();
    }
}
