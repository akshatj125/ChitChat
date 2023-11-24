package com.ChitChat.Conversations;

import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "conversations")
@Data
public class Conversations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "conversation", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Messages> messages;

    @CreationTimestamp
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @ManyToMany(mappedBy = "conversations")
    @JsonIgnore
    private List<Users> users;

    @Override
    public String toString() {
        return "Conversations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", messages=" + messages +
                ", timestamp=" + timestamp +
                '}';
    }
}
