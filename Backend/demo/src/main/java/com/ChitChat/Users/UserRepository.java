package com.ChitChat.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findById(int userId);

    Optional<Users> findByUsername(String username);

}

