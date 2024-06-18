package com.example.kiwi.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "학번이 비어있습니다.")
    private Short id;

    @NotBlank
    private String username;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
//    private String passwordCheck;

    @NotBlank(message = "이메일이 비어있습니다.")
    private String email;

    public User toEntity(String password){
        return User.builder()
                .ID(this.id)
                .password(password)
                .username(this.username)
                .email(this.email)
                .role(UserRole.USER)
                .enable(true)
                .build();
    }
}
