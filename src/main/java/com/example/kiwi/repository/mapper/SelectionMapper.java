package com.example.kiwi.repository.mapper;

import com.example.kiwi.domain.selection.DTO.FilterResponse;
import com.example.kiwi.domain.selection.SelectionMode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface SelectionMapper {
    @Select("SELECT u.gender,u.username,u.id,s.mode FROM user u NATURAL JOIN selection s WHERE mode = #{mode}")
    List<FilterResponse> getModeUser(SelectionMode mode);
}
