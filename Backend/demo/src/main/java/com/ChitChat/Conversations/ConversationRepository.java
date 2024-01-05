package com.ChitChat.Conversations;

import com.ChitChat.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversations, Integer> {

    Optional<Conversations> findById(int conversationId);

    Optional<Conversations> findByName(String name);

}