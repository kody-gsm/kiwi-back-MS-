package com.example.kiwi.domain.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserGender {
    MAN,WOMAN,UNKNOWN;

    @JsonCreator
    public static UserGender fromString(String value) {
        try {
            return UserGender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }

    @JsonValue
    public String toValue(){
        return name();
    }
}
