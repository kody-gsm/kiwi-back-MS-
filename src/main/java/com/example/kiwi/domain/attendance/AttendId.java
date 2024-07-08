package com.example.kiwi.domain.attendance;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AttendId implements Serializable {
    @JoinColumn(name = "id")
    private Short id;
    @CurrentTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime time;

    public AttendId(Short id) {
        this.id = id;
        this.time = LocalDateTime.now();
    }
}
