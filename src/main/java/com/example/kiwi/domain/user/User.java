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

    @Column(length = 6)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @Builder
    public User(Short ID,String username, String password, String email, UserRole role, UserGender gender) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.gender = gender;
    }
}

