package com.example.kiwi.domain.user;

import lombok.Data;

@Data
public class CheckRequest {
    private String username;
    private Short id;
    private UserGender gender;
}
