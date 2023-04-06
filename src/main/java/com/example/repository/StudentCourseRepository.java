package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {

    List<StudentCourseEntity> findAllById(Integer id);



}
