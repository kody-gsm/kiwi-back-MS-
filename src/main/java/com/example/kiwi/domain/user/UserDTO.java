package com.example.kiwi.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String password;
    private Short ID;
    private Boolean sex;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.ID = user.getId();
        this.sex = user.getSex();
    }

    @Builder
    public UserDTO(String username, String password, Short ID, Boolean sex) {
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.sex = sex;
    }
}
