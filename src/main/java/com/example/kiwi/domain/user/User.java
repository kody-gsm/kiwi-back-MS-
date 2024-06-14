package com.example.kiwi.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@NoArgsConstructor
@Getter
public class User {
    @Id
    private Short id;

    @Column(length = 5)
    private String username;

    @Column(length = 60)
    private String password;

    @Column(length = 7)
    private String email;

    @Column(nullable = false)
    private Boolean enable = true;

    @Builder
    public User(Short ID,String username, String password, String email, boolean enable) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enable = enable;
    }
}
