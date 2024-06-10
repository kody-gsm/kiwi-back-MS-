package com.example.kiwi.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private String password;
    private Short user_id;
    private Boolean gender;
    private String email;

    @Builder
    public UserDTO(String username, String password, Short ID, Boolean gender,String email) {
        this.username = username;
        this.password = password;
        this.user_id = ID;
        this.gender = gender;
        this.email = email;
    }
}
