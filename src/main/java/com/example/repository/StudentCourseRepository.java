package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {
}
