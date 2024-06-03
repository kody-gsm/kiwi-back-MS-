package com.example.kiwi.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT pass FROM user WHERE name = #{name}")
    String getpass(String name);

    @Update("UPDATE user SET #{option} + 1 WHERE ID = #{ID}")
    void addlate(short id, String option);

    @Select("SELECT pass FROM user WHERE email = #{email} AND name = #{name}")
    String getpass(String email, String name);
}
