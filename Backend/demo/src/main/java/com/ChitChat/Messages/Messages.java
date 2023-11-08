package com.ChitChat.Messages;

import com.ChitChat.Conversations.Conversations;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages") // This annotation specifies the name of the database table associated with this entity.
@Data // Lombok s annotation to generate getters, setters, equals, hashCode, and toString methods.
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversations conversation;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
