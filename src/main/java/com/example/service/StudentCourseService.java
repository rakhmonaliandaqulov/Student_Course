package com.example.service;

import com.example.dto.*;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentCourseService {
    private final StudentCourseRepository studentCourseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public Integer create(StudentCourseDto dto) {
        StudentCourseEntity entity = new StudentCourseEntity();

        StudentEntity student = studentService.get(dto.getStudentId());
        if (student == null) {
            throw new AppBadRequestException("Student not found: " + dto.getStudentId());
        }
        CourseEntity course = courseService.get(dto.getCourseId());
        if (course == null) {
            throw new AppBadRequestException("Course not found: " + dto.getCourseId());
        }

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());

        studentCourseRepository.save(entity);
        dto.setId(entity.getId());
        return entity.getId();
    }

    public Boolean update(Integer id, StudentCourseDto dto) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());

        studentCourseRepository.save(entity);
        return true;
    }

    public StudentCourseEntity get1(Integer id) {
        Optional<StudentCourseEntity> optional = studentCourseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        return optional.get();
    }

    public StudentCourseDto getById(Integer id) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentCourseDetailDto> getByIdWithDetail(Integer id) {
        List<StudentCourseDetailDto> list = new LinkedList<>();

        studentCourseRepository.findAllById(id).forEach(entity -> {
            StudentCourseDetailDto dto = new StudentCourseDetailDto();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(entity.getCourse().getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public Boolean deleteById(Integer id) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        studentCourseRepository.deleteById(id);
        return true;
    }

    public List<StudentCourseDto> getAll() {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAll();
        List<StudentCourseDto> dtoList = new LinkedList<>();

        for (StudentCourseEntity entity : iterable) {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());

            dtoList.add(dto);
        };
        return dtoList;
    }

    public List<StudentCourseDto> getByCreatedDateMark(LocalDateTime time) {
        List<StudentCourseEntity> list = studentCourseRepository.findByCreatedDateMark(time);
        if (list.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + time);
        }
        return toDTO(list);
    }
    public List<StudentCourseDto> toDTO(List<StudentCourseEntity> list){
        List<StudentCourseDto> dtos = new LinkedList<>();
        for(StudentCourseEntity entity : list){
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dtos.add(dto);
        }
        return dtos;
    }
}
