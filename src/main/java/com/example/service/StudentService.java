package com.example.service;

import com.example.dto.StudentDto;
import com.example.dto.StudentUpdateDTO;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Integer create(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setBirthDate(dto.getBirthDate());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());

        studentRepository.save(entity);
        return entity.getId();
    }
    public List<StudentDto> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDto> dtoList = new LinkedList<>();

        iterable.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;
    }
    public StudentDto getById(Integer id) {
        StudentEntity entity = get(id);
        return toDTO(entity);
    }

    public StudentDto toDTO(StudentEntity entity){
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());

        return dto;
    }



    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public Boolean updateById(Integer id, StudentDto studentDto) {
        StudentEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }

        entity.setName(studentDto.getName());
        entity.setSurname(studentDto.getSurname());
        entity.setAge(studentDto.getAge());
        entity.setBirthDate(studentDto.getBirthDate());
        entity.setLevel(studentDto.getLevel());
        entity.setGender(studentDto.getGender());

        studentRepository.save(entity);
        return true;
    }

    public Boolean deleteById(Integer id) {
        StudentEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        studentRepository.delete(entity);
        return true;
    }
}
