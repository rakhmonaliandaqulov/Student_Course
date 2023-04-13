package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer>,
        PagingAndSortingRepository<CourseEntity, Integer> {
    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByPrice(Double price);
    List<CourseEntity> findByDuration(String duration);
    List<CourseEntity> findByCreatedDateBetween(LocalDate date1, LocalDate date2);

    List<CourseEntity> findByPriceBetween(Double price1, Double price2);
    Page<CourseEntity> findAllByPrice(Double price, Pageable pageable);

    Page<CourseEntity> findByCreatedDateBetween(LocalDate date1, LocalDate date2, Pageable pageable);
}
