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

    public void save(User user) {
        userRep.save(user);
    }

    public String getpass(String name){
        return userMapper.getpass(name);
    }

    public User getname(String name){
        return userRep.findByUsername(name);
    }

    public boolean namecheck(String name){
        return getname(name) == null;
    }

    public boolean login(String name, String password) {
        String pass = userMapper.getpass(name);
        return pass.equals(password);
    }

    public void addlate(short id){
        userMapper.addlate(id);
    }
}
