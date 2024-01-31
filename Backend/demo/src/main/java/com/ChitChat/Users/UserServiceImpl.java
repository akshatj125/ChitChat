package com.ChitChat.Users;

import com.ChitChat.Conversations.ConversationRepository;
import com.ChitChat.DTO.ConversationDto.ConversationDto;
import com.ChitChat.DTO.ConversationDto.ConversationMapper;
import com.ChitChat.DTO.UserDetailDto.UserDetailDto;
import com.ChitChat.DTO.UserDetailDto.UserDetailMapper;
import com.ChitChat.ProfilePicture.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final ProfilePictureService profilePictureService;

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

        String profilePictureId = profilePictureService.storeProfilePicture(profilePicture);

        user.setProfilepicture(profilePictureId);

        userRepository.save(user);

        return profilePictureId;
    }

    public byte[] getProfilePicture(String profilePictureId) {
        if (profilePictureId != null) {
            return profilePictureService.getProfilePicture(profilePictureId);
        }
        return null;
    }

}