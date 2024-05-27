package com.example.kiwi.domain.attandance;

import com.example.kiwi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.java.ShortPrimitiveArrayJavaType;

@Entity
@Table(name = "Attend")
@NoArgsConstructor
@Getter
public class Attendance {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Short etc_absent;
    private Short absent;
    private Short reco_absent;
    private Short dise_absent;

    private Short etc_late;
    private Short late;
    private Short reco_late;
    private Short dise_late;

    private Short etc_leave;
    private Short early_leave;
    private Short reco_leave;
    private Short dise_leave;

    @Builder
    public Attendance(Short etc_absent, Short absent, Short reco_absent, Short dise_absent, Short etc_late, Short late, Short dise_late, Short reco_late, Short early_leave, Short dise_leave, Short etc_leave, Short reco_leave){
        this.etc_absent = etc_absent;
        this.absent = absent;
        this.reco_absent = reco_absent;
        this.dise_absent = dise_absent;
        this.etc_late = etc_late;
        this.late = late;
        this.dise_late = dise_late;
        this.reco_late = reco_late;
        this.early_leave = early_leave;
        this.dise_leave = dise_leave;
        this.etc_leave = etc_leave;
        this.reco_leave = reco_leave;
    }
}
