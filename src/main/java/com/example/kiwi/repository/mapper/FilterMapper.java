package com.example.kiwi.repository.mapper;

import com.example.kiwi.domain.selection.DTO.FilterResponse;
import com.example.kiwi.domain.selection.SelectionMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FilterMapper {
    @Select("SELECT u.gender,u.username,u.id,s.mode FROM user u NATURAL JOIN selection s WHERE mode = #{mode} AND u.id >= #{id} AND u.id <= #{id}+418")
    List<FilterResponse> getFilterGrade(SelectionMode mode, Short id);

    @Select("SELECT u.gender,u.username,u.id,s.mode FROM user u NATURAL JOIN selection s WHERE mode = #{mode} AND u.id >= #{id} AND u.id <= #{id}+18")
    List<FilterResponse> getFilterClass(SelectionMode mode, Short id);
}
