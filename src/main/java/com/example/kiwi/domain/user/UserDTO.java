package com.example.kiwi.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String password;
    private Short user_id;
    private Boolean gender;
    private String email;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.user_id = user.getUser_id();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }

    @Builder
    public UserDTO(String username, String password, Short ID, Boolean gender,String email) {
        this.username = username;
        this.password = password;
        this.user_id = ID;
        this.gender = gender;
        this.email = email;
    }
}
