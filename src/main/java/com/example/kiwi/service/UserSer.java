package com.example.kiwi.service;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSer {
    private final UserRep userRep;

    @Transactional
    public void save(User user) {
        userRep.save(user);
    }
}
