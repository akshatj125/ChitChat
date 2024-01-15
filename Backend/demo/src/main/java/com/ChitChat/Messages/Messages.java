package com.ChitChat.Messages;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Users.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
//    @JsonBackReference
    private Conversations conversation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "seen")
    private boolean seen;

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", seen=" + seen +
                '}';
    }
}