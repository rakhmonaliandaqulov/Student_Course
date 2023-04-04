package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
        entity.setGender(dto.getGender());

        studentRepository.save(entity);
        dto.setId(entity.getId());
        dto.setGender(entity.getGender());
        return dto;
    }
    public List<StudentDto> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDto> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setBirthDate(entity.getBirthDate());
            dto.setGender(entity.getGender());

            dtoList.add(dto);
        });
        return dtoList;
    }
}
