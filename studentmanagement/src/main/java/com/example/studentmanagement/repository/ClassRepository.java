package com.example.studentmanagement.repository;

import com.example.studentmanagement.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findBySubjectId(Long subjectId);
}
