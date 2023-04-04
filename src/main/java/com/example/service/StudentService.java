package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentDto create(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setBirthDate(dto.getBirthDate());
        entity.setLevel(dto.getLevel());
        entity.setGender(StudentGender.MALE);

        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
}
