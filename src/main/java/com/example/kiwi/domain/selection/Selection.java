package com.example.kiwi.domain.selection;

import com.example.kiwi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Selection {
    @EmbeddedId
    private SelectionId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    @MapsId("id")
    private User user;

    @Builder
    public Selection(User user) {
        this.id = new SelectionId(user.getId(), SelectionMode.NONE);
        this.user = user;
    }
}
