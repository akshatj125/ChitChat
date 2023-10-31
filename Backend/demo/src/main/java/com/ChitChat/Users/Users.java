package com.ChitChat.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ChitChat.DTO.UserDetailDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "users") // This annotation specifies the name of the database table associated with this entity.
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods.
public class Users {

    @Id // Indicates that this field is the primary key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies how the primary key value is generated (identity strategy for auto-increment).
    private int id;

    @Column(name = "username") // Maps this field to the "username" column in the database table.
    private String username;

    @Column(name = "email") // Maps this field to the "email" column in the database table.
    private String email;

    @Column(name = "profilepicture") // Maps this field to the "profilepicture" column in the
    private String profilepicture;

    @CreationTimestamp
    // JPA Hibernate annotation that automatically sets the current timestamp when the entity is created.
    @Column(name = "created_at", nullable = false, updatable = false)
    // Maps this field to the "created_at" column in the database table.
    private LocalDateTime created_at;

    @Column(name = "status") // Maps this field to the "status" column in the database table.
    private boolean status;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL, fetch =FetchType.EAGER)
    private List<Conversations> conversations;

    @OneToMany(mappedBy = "receiver")
    private List<ReceivedMessages> receivedMessages;

    public static UserDetailDto convertToUserDetailDto(Users user){

        UserDetailDto userDetailDto= new UserDetailDto(user.getUsername());
        System.out.println("In userdto: ");
        System.out.println(userDetailDto);
        return userDetailDto;
    }

    public static List<UserDetailDto> convertToUserDetailDto(List<Users> users){
        return users.stream().map(Users::convertToUserDetailDto).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilepicture='" + profilepicture + '\'' +
                ", created_at=" + created_at +
                ", status=" + status +
                '}';
    }
}
