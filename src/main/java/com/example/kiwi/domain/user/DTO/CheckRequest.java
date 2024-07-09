package com.example.kiwi.domain.user.DTO;

import com.example.kiwi.domain.user.UserGender;
import lombok.Data;

@Data
public class CheckRequest {
    private String username;
    private Short id;
    private UserGender gender;
}
