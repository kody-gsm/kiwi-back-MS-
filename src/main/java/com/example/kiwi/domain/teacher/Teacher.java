package com.example.kiwi.domain.teacher;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "Teacher")
@Entity
@NoArgsConstructor
public class Teacher{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Builder
    public Teacher(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
