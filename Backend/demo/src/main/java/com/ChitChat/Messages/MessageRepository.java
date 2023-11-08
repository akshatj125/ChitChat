package com.ChitChat.Messages;

import com.ChitChat.Conversations.Conversations;
import com.ChitChat.Messages.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Messages, Integer> {

    Optional<Messages> findById(int messageId);
}
