package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
    Optional<CourseEntity> findByName(String name);
    Optional<CourseEntity> findByPrice(Double price);
    Optional<CourseEntity> findByDuration(String duration);
}
