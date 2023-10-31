package com.ChitChat.ReceivedMessages;

import com.ChitChat.Messages.Messages;
import com.ChitChat.Users.Users;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Received_messages")
@Data
public class ReceivedMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiverId")
    private Users receiver;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "messageId")
    private Messages message;

    @Column(name = "read")
    private boolean read;

    @CreationTimestamp
    @Column(name = "read_at", nullable = false, updatable = false)
    private LocalDateTime read_at;

    @Override
    public String toString() {
        return "ReceivedMessages{" +
                "id=" + id +
                ", receiver=" + receiver +
                ", message=" + message +
                ", read=" + read +
                ", read_at=" + read_at +
                '}';
    }
}
