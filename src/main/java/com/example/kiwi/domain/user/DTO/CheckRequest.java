package com.example.kiwi.domain.user.DTO;

import com.example.kiwi.domain.user.UserGender;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckRequest {
    private String username;
    private Short id;
    private UserGender gender;
    private Short attendance;

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

    private Short etc_outing;
    private Short outing;
    private Short reco_outing;
    private Short dise_outing;

    @Builder
    public CheckRequest(Short id,Short etc_absent, Short absent, Short reco_absent, Short dise_absent, Short etc_late, Short late, Short dise_late, Short reco_late, Short early_leave, Short dise_leave, Short etc_leave, Short reco_leave, Short attendance,String username,UserGender gender,Short outing,Short dise_outing,Short reco_outing,Short etc_outing){
        this.id=id;
        this.etc_absent=etc_absent;
        this.absent=absent;
        this.reco_absent=reco_absent;
        this.dise_absent=dise_absent;
        this.etc_late=etc_late;
        this.late=late;
        this.dise_late=dise_late;
        this.reco_late=reco_late;
        this.early_leave=early_leave;
        this.dise_leave=dise_leave;
        this.etc_leave=etc_leave;
        this.reco_leave=reco_leave;
        this.attendance=attendance;
        this.username=username;
        this.gender=gender;
        this.outing=outing;
        this.dise_outing=dise_outing;
        this.reco_outing=reco_outing;
        this.etc_outing=etc_outing;
    }
}
