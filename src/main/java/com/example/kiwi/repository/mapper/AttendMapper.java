package com.example.kiwi.repository.mapper;

import com.example.kiwi.domain.user.DTO.CheckRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Mapper
@Transactional
public interface AttendMapper {
    @Select("SELECT attendance,etc_absent,absent,reco_absent,dise_absent,etc_late,late,reco_late,dise_late,etc_leave,early_leave,reco_leave,dise_leave,outing,reco_outing,dise_outing,etc_outing FROM Attend WHERE id = #{id} AND time = (SELECT MAX(a.time) FROM Attend a WHERE a.id = #{id})")
    Optional<CheckRequest> findAttendanceById(Short id);
}
