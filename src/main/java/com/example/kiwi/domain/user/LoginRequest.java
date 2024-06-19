package com.example.kiwi.domain.user;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
