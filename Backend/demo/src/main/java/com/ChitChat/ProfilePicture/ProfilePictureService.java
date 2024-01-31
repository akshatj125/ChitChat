package com.ChitChat.ProfilePicture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePictureService {

    @Autowired
    private ProfilePictureRepository profilePictureRepository;

    public String storeProfilePicture(MultipartFile file) throws IOException {
        byte[] content = file.getBytes();
        ProfilePicture profilePicture = new ProfilePicture(content);
        ProfilePicture savedPicture = profilePictureRepository.save(profilePicture);
        return savedPicture.getId();
    }

    public byte[] getProfilePicture(String fileId) {
        return profilePictureRepository.findById(fileId).get().getContent();
    }
}