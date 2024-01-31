package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Users save(Users user);

    List<Users> findAll();

    Optional<Users> findById(int theId);

    Optional<Users> findByUsername(String username);

    List<ConversationDto> conversationsPerUser(Authentication authentication);

    List<UserDetailDto> searchUsers(String query);

    String updateProfilePicture(Authentication authentication, MultipartFile profilePicture) throws IOException;

//    byte[] getProfilePicture(Authentication authentication);

    byte[] getProfilePicture(String profilePictureId);

}
