package com.example.kiwi.domain.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(nullable = false, length = 20)
    @Size(min = 8, max = 20)
    private String password;

    @Column(nullable = false)
    private Boolean gender;

    @Column(nullable = false, length = 7)
    private String email;

    private Short late;

    private Short recognized;

    private Short truancy;

    private Short attend;

    @Builder
    public User(Short ID,String username, String password, Boolean gender, String email) {
        this.id = ID;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.email = email;
    }
}
