package com.example.kiwi.service;

import com.example.kiwi.domain.user.SignUpRequest;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserSer {

    private final UserRep userRep;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private User user;

    public String encodePW(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkLoginID(Short id){
        return userRep.existsById(id);
    }

    public boolean checkEmail(String email){
        return userRep.existsByEmail(email);
    }

    public void signUp(SignUpRequest req){
        userRep.save(req.toEntity(encodePW(req.getPassword())));
    }

    public Optional<User> checkPEI(String email, String name, Short id){

        return userRep.findByUsernameAndIdAndEmail(name,id,email);
    }
}
