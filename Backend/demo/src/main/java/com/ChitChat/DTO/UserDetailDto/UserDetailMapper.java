package com.ChitChat.DTO.UserDetailDto;

import com.ChitChat.Users.Users;

import java.util.List;

public class UserDetailMapper {

    public static UserDetailDto mapToUserDto(Users user){
        return new UserDetailDto(
                user.getUsername(),
                user.getEmail(),
                user.getProfilepicture()
        );
    }
    public static List<UserDetailDto> mapToUserDto(List<Users> users){
        return users.stream().map(UserDetailMapper::mapToUserDto).toList();
    }
}
