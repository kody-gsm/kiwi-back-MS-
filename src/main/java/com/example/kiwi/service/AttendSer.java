package com.example.kiwi.service;

import com.example.kiwi.domain.attandance.Attend;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.AttendRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendSer {
    private final AttendRep attendRep;

    public void CreateAttendance(Short id) {
        Attend attend = attendRep.save(
                Attend.builder()
                        .id(id)
                        .absent((short) 0)
                        .dise_absent((short) 0)
                        .etc_absent((short) 0)
                        .reco_absent((short) 0)
                        .late((short) 0)
                        .dise_late((short) 0)
                        .etc_late((short) 0)
                        .reco_late((short) 0)
                        .early_leave((short) 0)
                        .dise_leave((short) 0)
                        .etc_leave((short) 0)
                        .reco_leave((short) 0)
                        .build()
        );
    }
}
