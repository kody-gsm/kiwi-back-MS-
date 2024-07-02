package com.example.kiwi.service.domainSer;

import com.example.kiwi.domain.attendance.Attend;
import com.example.kiwi.domain.user.DTO.CheckRequest;
import com.example.kiwi.domain.user.User;
import com.example.kiwi.repository.AttendRep;
import com.example.kiwi.repository.UserRep;
import com.example.kiwi.repository.mapper.AttendMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AttendSer {
    private final AttendRep attendRep;
    private final UserRep userRep;
    private final AttendMapper attendMapper;

    public Optional<CheckRequest> SelectAttendance(Short id) {
        if (attendMapper.findAttendanceById(id).isPresent()) {
            return attendMapper.findAttendanceById(id);
        }
        return Optional.empty();
    }

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
                        .attendance((short) 0)
                        .outing((short) 0)
                        .dise_outing((short) 0)
                        .etc_outing((short) 0)
                        .reco_outing((short) 0)
                        .build()
        );
    }
}
