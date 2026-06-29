package com.hurryflex.hurryflex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hurryflex.hurryflex.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}