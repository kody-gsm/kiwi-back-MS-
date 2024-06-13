package com.example.kiwi.service;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSer {

    private final UserRep userRep;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private User user;

    public UserDTO getDto(String Email){
        User users = userRep.findByEmail(Email);
        if(users != null) {
            return UserDTO.builder()
                    .ID(users.getId())
                    .username(users.getUsername())
                    .password(users.getPassword())
                    .email(users.getEmail())
                    .build();
        }
        else
            return null;
    }

    public String encodePW(String password) {
        return passwordEncoder.encode(password);
    }

    public String getpass1(String name, String email){
        return userMapper.getpass1(name, email);
    }

    public boolean login(String name, String password) {
        String pass = userMapper.getpass(name);
        return pass.equals(password);
    }

    public void addlate(short id,String option){
        userMapper.addlate(id,option);
    }
}
