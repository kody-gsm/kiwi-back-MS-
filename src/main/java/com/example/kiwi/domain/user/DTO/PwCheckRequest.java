package com.example.kiwi.domain.user.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PwCheckRequest {
    @NotBlank(message = "이메일이 비어있습니다.")
    String email;

    @NotBlank(message = "이름이 비어있습니다.")
    String name;

    @NotNull(message = "학번이 비어있습니다.")
    Short id;
}
