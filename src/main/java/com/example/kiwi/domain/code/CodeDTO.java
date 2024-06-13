package com.example.kiwi.domain.code;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CodeDTO {
    private String code;

    @Builder
    public CodeDTO(String code) {
        this.code = code;
    }
}
