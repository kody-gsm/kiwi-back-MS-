package com.example.kiwi.repository;

import com.example.kiwi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRep extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    User findByEmail(String email);
    User findById(Short id);
}