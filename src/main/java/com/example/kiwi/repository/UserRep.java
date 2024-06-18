package com.example.kiwi.repository;

import com.example.kiwi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRep extends JpaRepository<User, Long> {
    boolean existsById(Short id);
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
}