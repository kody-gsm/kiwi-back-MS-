package com.example.kiwi.domain.attandance;

import com.example.kiwi.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AttendDTO {

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
    public AttendDTO(Short etc_late, Short etc_leave, Short etc_absent, Short absent, Short reco_absent, Short dise_absent, Short late, Short reco_late, Short dise_late, Short early_leave, Short reco_leave, Short dise_leave) {
        this.etc_late = etc_late;
        this.etc_absent = etc_absent;
        this.absent = absent;
        this.reco_absent = reco_absent;
        this.dise_absent = dise_absent;
        this.early_leave = early_leave;
        this.late = late;
        this.reco_late = reco_late;
        this.dise_late = dise_late;
        this.etc_leave = etc_leave;
        this.reco_leave = reco_leave;
        this.dise_leave = dise_leave;
    }
}
