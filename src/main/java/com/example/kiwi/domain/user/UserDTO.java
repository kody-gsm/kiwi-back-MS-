package com.example.kiwi.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDTO {
    private String username;
    private String password;
    private Short ID;
    private Boolean gender;
    private String email;
    private Short late;
    private Short recognized;
    private Short truancy;
    private Short attend;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.ID = user.getId();
        this.gender = user.getGender();
        this.email = user.getEmail();
    }

    @Builder
    public UserDTO(String username, String password, Short ID, Boolean gender,String email) {
        this.username = username;
        this.password = password;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
    }

    @Builder
    public void Check(Short late,Short recognized,Short truancy,Short attend){
        this.late = late;
        this.recognized = recognized;
        this.truancy = truancy;
        this.attend = attend;
    }
}
