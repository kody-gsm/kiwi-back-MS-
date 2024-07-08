package com.example.kiwi.domain.user;

import com.example.kiwi.domain.attendance.Attend;
import com.example.kiwi.domain.selection.Selection;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Attend> attends = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Selection> selection = new ArrayList<>();

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

