package com.example.kiwi.repository.mapper;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Update("UPDATE user SET late + 1 WHERE ID = (#{ID})")
    void addlate(short id);
}
