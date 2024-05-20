package com.example.kiwi.repository.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Update("UPDATE user SET late + 1 WHERE ID = (#{ID})")
    void addlate(short id);

    @Select("SELECT pass FROM user WHERE name = (#{name})")
    String getpass(String name);
}
