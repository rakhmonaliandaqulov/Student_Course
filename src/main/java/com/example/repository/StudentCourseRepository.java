package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {

    List<StudentCourseEntity> findAllById(Integer id);

    @Query("from StudentCourseEntity where createdDate = :time")
    List<StudentCourseEntity> findByCreatedDateMark(@Param("time") LocalDateTime time);
}
