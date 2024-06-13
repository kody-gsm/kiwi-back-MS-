package com.example.kiwi.domain.code;

import com.example.kiwi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "code")
@Entity
@NoArgsConstructor
@Getter
public class Code {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 10)
    private String code;

    @Builder
    public Code(String code) {
        this.code = code;
    }
}
