package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByPrice(Double price);
    List<CourseEntity> findByDuration(String duration);
}
