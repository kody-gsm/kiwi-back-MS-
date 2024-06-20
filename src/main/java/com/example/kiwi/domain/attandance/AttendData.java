package com.example.kiwi.domain.attandance;

import com.example.kiwi.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendData {
    private Short id;

    public User createAttend(){
        return User.builder()
                .ID(this.id)
                .build();
    }
}
