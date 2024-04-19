package com.example.kiwi.repository;

import com.example.kiwi.domain.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRep extends JpaRepository<Teacher, Long> {
}
