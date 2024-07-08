package com.example.kiwi.domain.selection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SelectionId implements Serializable {
    @JoinColumn(name = "id")
    private Short id;

    @Enumerated(EnumType.STRING)
    private SelectionMode mode;
}
