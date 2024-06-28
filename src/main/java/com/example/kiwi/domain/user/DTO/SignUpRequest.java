package com.example.kiwi.domain.user.DTO;

import com.example.kiwi.domain.user.User;
import com.example.kiwi.domain.user.UserGender;
import com.example.kiwi.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @NotNull(message = "학번이 비어있습니다.")
    private Short id;

    @NotBlank
    private String username;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    private String passwordCheck;

    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;

    @NotNull(message = "성별이 비어있습니다.")
    private UserGender gender;

    public User toEntity(String password){
        return User.builder()
                .ID(this.id)
                .password(password)
                .username(this.username)
                .email(this.email)
                .role(UserRole.USER)
                .gender(this.gender)
                .build();
    }
}
