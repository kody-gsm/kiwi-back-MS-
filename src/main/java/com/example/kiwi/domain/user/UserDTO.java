package com.example.kiwi.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private String password;
    private Short id;
    private String email;
    private Boolean enable;
    private UserRole role;

    @Builder
    public UserDTO(String username, String password, Short ID,String email, Boolean enable, UserRole role) {
        this.username = username;
        this.password = password;
        this.id = ID;
        this.email = email;
        this.enable = enable;
        this.role = role;
    }
}
