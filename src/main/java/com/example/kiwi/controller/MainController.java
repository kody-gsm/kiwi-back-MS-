package com.example.kiwi.controller;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserDTO;
import com.example.kiwi.service.UserSer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {
    private final UserSer userSer;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User user = new User();
        String name = userDTO.getUsername();
        String pass = userDTO.getPassword();

        if(user.getUsername().equals(name) && user.getPassword().equals(pass)) {
            return ResponseEntity.ok("Hello "+name);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid username or password");
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUP(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .ID(userDTO.getID())
                .email(userDTO.getEmail())
                .gender(userDTO.getGender())
                .build();

        userSer.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
