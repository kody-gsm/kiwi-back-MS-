package com.example.kiwi.repository.mapper;

import com.example.kiwi.domain.selection.DTO.FilterResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT u.gender,u.username,u.id,s.mode FROM user u NATURAL JOIN selection s WHERE u.id >= #{id} AND u.id <= #{id} + 418")
    List<FilterResponse> getGradeUser(Short id);

    @Select("SELECT u.gender,u.username,u.id,s.mode FROM user u NATURAL JOIN selection s WHERE u.id >= #{id} AND u.id <= #{id} + 18")
    List<FilterResponse> getClassUser(Short id);
}
