package com.ChitChat.DTO.LoginDto;

import com.ChitChat.Users.Users;

import java.util.List;

public class LoginMapper {

    public static LoginDto mapToUserDto(Users user) {
        return new LoginDto(
                user.getEmail(),
                user.getPassword()
        );
    }

    public static List<LoginDto> mapToUserDto(List<Users> users){
        return users.stream().map(LoginMapper::mapToUserDto).toList();
    }
}
