package com.ChitChat.DTO.UserDetailDto;

import com.ChitChat.Users.Users;

import java.util.List;

public class UserDetailMapper {

    // Convert User JPA Entity into UserDto
    public static UserDetailDto mapToUserDto(Users user){
        return new UserDetailDto(
                user.getUsername(),
                user.getEmail(),
                user.getProfilepicture(),
                user.getCreated_at()
        );
    }
    public static List<UserDetailDto> mapToUserDto(List<Users> users){
        return users.stream().map(UserDetailMapper::mapToUserDto).toList();
    }
}