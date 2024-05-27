package com.example.kiwi.service;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSer {
    private final UserRep userRep;
    private final UserMapper userMapper;

    public String getpass(String name, String email){
        return userMapper.getpass(name, email);
    }

    public boolean login(String name, String password) {
        String pass = userMapper.getpass(name);
        return pass.equals(password);
    }

    public void addlate(short id){
        userMapper.addlate(id);
    }
}
