package com.example.kiwi.domain.selection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SelectionMode {
    ATTENDANCE,OUTING,LEAVE,ABSENT,NONE;

    @JsonCreator
    public static SelectionMode fromString(String value) {
        try {
            return SelectionMode.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }

    @JsonValue
    public String toValue(){
        return name();
    }
}
