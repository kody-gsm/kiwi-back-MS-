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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(nullable = false, length = 5)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 7)
    private String email;

    @Builder
    public User(Short ID,String username, String password, Boolean gender, String email) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
