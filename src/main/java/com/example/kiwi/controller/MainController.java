package com.example.kiwi.controller;

import com.example.kiwi.domain.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@Transactional
@RequiredArgsConstructor
public class MainController {
    User user = new User();
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String username, @RequestBody String password) {
        String name = user.getUsername();
        String pass = user.getPassword();

        return ResponseEntity.ok("Hello " + username);
    }
}
