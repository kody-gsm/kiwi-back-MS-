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

    @Builder
    public UserDTO(String username, String password, Short ID,String email) {
        this.username = username;
        this.password = password;
        this.id = ID;
        this.email = email;
    }
}
