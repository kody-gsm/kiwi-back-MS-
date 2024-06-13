package com.example.kiwi.domain.user;

import jakarta.persistence.*;
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

    @Builder
    public User(Short ID,String username, String password, String email) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
