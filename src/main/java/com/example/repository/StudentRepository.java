package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    List<StudentEntity> findBySurname(String surname);
    List<StudentEntity> findByLevel(Integer level);
    List<StudentEntity> findByAge(Integer age);

    List<StudentEntity> findByBirthDate(LocalDate date);

    List<StudentEntity> findByName(String name);

    List<StudentEntity> findByGender(StudentGender gender);
}
