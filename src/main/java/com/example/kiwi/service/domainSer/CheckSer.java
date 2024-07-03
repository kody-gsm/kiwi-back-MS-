package com.example.kiwi.service.domainSer;

import com.example.kiwi.domain.user.DTO.CheckRequest;
import com.example.kiwi.domain.user.UserGender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CheckSer {
    public CheckRequest checkSelcet(Optional<CheckRequest> userAttend, Short id, String name, UserGender gender) {
        if (userAttend.isPresent()) {
            return CheckRequest.builder()
                    .attendance(userAttend.get().getAttendance())

                    .absent(userAttend.get().getAbsent())
                    .etc_absent(userAttend.get().getEtc_absent())
                    .reco_absent(userAttend.get().getReco_absent())
                    .dise_absent(userAttend.get().getDise_absent())

                    .late(userAttend.get().getLate())
                    .etc_late(userAttend.get().getEtc_late())
                    .reco_late(userAttend.get().getReco_late())
                    .dise_late(userAttend.get().getDise_absent())

                    .early_leave(userAttend.get().getEarly_leave())
                    .etc_leave(userAttend.get().getEtc_leave())
                    .reco_leave(userAttend.get().getReco_leave())
                    .dise_leave(userAttend.get().getDise_leave())

                    .outing(userAttend.get().getOuting())
                    .etc_outing(userAttend.get().getEtc_outing())
                    .reco_outing(userAttend.get().getReco_outing())
                    .dise_outing(userAttend.get().getDise_outing())

                    .gender(gender)
                    .username(name)
                    .id(id)
                    .build();
        }
        else
            return null;
}}
