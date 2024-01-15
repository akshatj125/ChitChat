package com.ChitChat.Messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Messages, Integer> {

    Optional<Messages> findById(int messageId);

    @Modifying
    @Transactional
    @Query("UPDATE Messages SET seen = :seen WHERE id= :messageId")
    void updateStatus(@Param("messageId") int messageId,@Param("seen")  boolean seen);
}
