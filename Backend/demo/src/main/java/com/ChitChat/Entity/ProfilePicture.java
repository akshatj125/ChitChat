package com.ChitChat.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
@Table(name = "profilepic")
@Data
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "profilepic")
    private byte[] profilepic;

    public void setProfilePic(byte[] theprofilepic) {
        this.profilepic = theprofilepic;
    }

}
