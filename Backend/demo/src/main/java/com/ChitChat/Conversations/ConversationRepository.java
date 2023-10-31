package com.ChitChat.Conversations;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversations, Integer> {

    Optional<Conversations> findById(int conversationId);

}
