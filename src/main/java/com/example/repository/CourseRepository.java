package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>,
        PagingAndSortingRepository<CourseEntity, Integer> {
    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByPrice(Double price);
    List<CourseEntity> findByDuration(String duration);
    List<CourseEntity> findByPriceBetween(Double price1, Double price2);
    List<CourseEntity> findByCreatedDateBetween(LocalDate date1, LocalDate date2);

//    Page<CourseEntity> findAll(int page, int size);
}
