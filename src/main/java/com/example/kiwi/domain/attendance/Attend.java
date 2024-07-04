package com.example.kiwi.domain.attendance;

import com.example.kiwi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Attend")
@NoArgsConstructor
@Getter
public class Attend {
    @EmbeddedId
    private AttendId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id")
    @MapsId("id")
    private User user;

    private Short attendance; //출석

    private Short etc_absent; //기타 결석
    private Short absent; //결석
    private Short reco_absent; //인정 결석
    private Short dise_absent; //질병 결석

    private Short etc_late; //기타 지각
    private Short late; //지각
    private Short reco_late; //인정 지각
    private Short dise_late; //질병 지각

    private Short etc_leave; //기타 조퇴
    private Short early_leave; //조퇴
    private Short reco_leave; //인정 조퇴
    private Short dise_leave; //질병 조퇴

    private Short etc_outing; //기타 외출
    private Short outing; //외출
    private Short reco_outing; //인정 외출
    private Short dise_outing; //질병 외출

    @Builder
    public Attend(User user, Short etc_absent, Short absent, Short reco_absent, Short dise_absent, Short etc_late, Short late, Short dise_late, Short reco_late, Short early_leave, Short dise_leave, Short etc_leave, Short reco_leave, Short attendance,Short outing,Short dise_outing,Short reco_outing,Short etc_outing){
        this.id = new AttendId(user.getId());
        this.user = user;
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
        this.attendance = attendance;
        this.outing=outing;
        this.dise_outing=dise_outing;
        this.reco_outing=reco_outing;
        this.etc_outing=etc_outing;
    }
}