package com.example.repository;

import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    Optional<StudentEntity> findByName(String name);
    Optional<StudentEntity> findBySurname(String surname);
    Optional<StudentEntity> findByLevel(Integer level);
    Optional<StudentEntity> findByAge(Integer age);
    Optional<StudentEntity> findByGender(String gender);
}
