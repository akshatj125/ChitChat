package com.ChitChat.Users;

import java.time.LocalDateTime;
import java.util.List;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Messages.Messages;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@Table(name = "users") // This annotation specifies the name of the database table associated with this entity.
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id // Indicates that this field is the primary key of the database table.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Specifies how the primary key value is generated (identity strategy for auto-increment).
    private int id;

    @Column(name = "username", unique = true) // Maps this field to the "username" column in the database table.
    private String username;

    @PrePersist
    public void beforeSave() {
        if (username != null) {
            username = username.toLowerCase();
        }
    }
    @Column(name = "email") // Maps this field to the "email" column in the database table.
    private String email;

    @Column(name = "profilepicture") // Maps this field to the "profilepicture" column in the
    private String profilepicture;

    @Column(name = "password") // Maps this field to the "password" column in the database table.
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "status") // Maps this field to the "status" column in the database table.
    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_conversations",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private List<Conversations> conversations;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Messages> messages;

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilepicture='" + profilepicture + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                ", status=" + status +
                '}';
    }
}
