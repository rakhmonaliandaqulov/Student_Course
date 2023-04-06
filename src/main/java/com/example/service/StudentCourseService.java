package com.example.service;

import com.example.dto.StudentCourseDto;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {
    private StudentCourseRepository studentCourseRepository;
    /*public StudentCourseDto create(StudentCourseDto dto) {
        StudentCourseEntity entity = new StudentCourseEntity();
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());

        studentCourseRepository.save(entity);
        return entity.getId();
    }*/
}
