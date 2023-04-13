package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer>,
        PagingAndSortingRepository<StudentEntity, Integer> {
    List<StudentEntity> findBySurname(String surname);
    List<StudentEntity> findByLevel(Integer level);
    List<StudentEntity> findByAge(Integer age);
    List<StudentEntity> findByBirthDate(LocalDate date);
    List<StudentEntity> findByName(String name);
    List<StudentEntity> findByGender(StudentGender gender);
    List<StudentEntity> findByBirthDateBetween(LocalDate date1, LocalDate date2);
    Page<StudentEntity> findAllByName(String name, Pageable pageable);

    Page<StudentEntity> findAllByLevel(Integer level, Pageable paging);
    Page<StudentEntity> findAllByGender(StudentGender gender, Pageable paging);
}
