package com.ChitChat.Messages;

import com.ChitChat.Messages.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages, Integer> {
}
