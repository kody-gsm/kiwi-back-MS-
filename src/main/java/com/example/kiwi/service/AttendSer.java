package com.example.kiwi.service;

import com.example.kiwi.domain.attandance.Attend;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.AttendRep;
import com.example.kiwi.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendSer {
    private final AttendRep attendRep;
    private final UserRep userRep;

    public void CreateAttendance(Short id) {
        User user = userRep.findUserById(id);
        Attend attend = attendRep.save(
                Attend.builder()
                        .user(user)
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
