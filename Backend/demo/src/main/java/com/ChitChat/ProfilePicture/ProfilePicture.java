package com.ChitChat.ProfilePicture;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "profile_pictures")
public class ProfilePicture {

    @Id
    private String id;

    private byte[] content;

    public ProfilePicture(byte[] content) {
        this.content = content;
    }
}
