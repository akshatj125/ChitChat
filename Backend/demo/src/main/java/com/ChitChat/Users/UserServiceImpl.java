package com.ChitChat.Users;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import com.ChitChat.ProfilePicture.GridFsService;
import com.ChitChat.exceptions.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final GridFsService gridFsService;

    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(int theId) {
        return userRepository.findById(theId);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Users addUserToConversation(int conversationId, Authentication authentication) {
//        System.out.println("hello");
        Users user = (Users) authentication.getPrincipal();
        Conversations conversation = conversationRepository.findById(conversationId).orElseThrow(()->new AppException("Conversation not found", HttpStatus.NOT_FOUND));
        user.getConversations().add(conversation);
        userRepository.save(user);
        return user;
    }

    @Override
    public void removeUser(int userId) {
        Users user = userRepository.findById(userId).orElseThrow(()->new AppException("Could not find user", HttpStatus.NOT_FOUND));
        userRepository.deleteById(userId);
    }

    @Override
    public List<ConversationDto> conversationsPerUser(Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();
        return ConversationMapper.mapToConversationDto(user.getConversations(),user.getUsername());
    }

    @Override
    public List<UserDetailDto> searchUsers(String query) {
        List<Users> users = userRepository.findByUsernameContaining(query);
        return users.stream()
                .map(UserDetailMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public String updateProfilePicture(Authentication authentication, MultipartFile profilePicture) throws IOException {
        Users user = (Users) authentication.getPrincipal();

        String profilePictureId = gridFsService.storeProfilePicture(profilePicture);

        user.setProfilepicture(profilePictureId);

        userRepository.save(user);

        return profilePictureId;
    }

    public byte[] getProfilePicture(String profilePictureId) {
        if (profilePictureId != null) {
            return gridFsService.getProfilePicture(profilePictureId);
        }
        return null;
    }


//    public byte[] getProfilePicture(Authentication authentication) {
//        Users user = (Users) authentication.getPrincipal();
//
//        String profilePictureId = user.getProfilepicture();
//        if (profilePictureId != null) {
//            return gridFsService.getProfilePicture(profilePictureId);
//        }
//
//        return null;
//    }
}