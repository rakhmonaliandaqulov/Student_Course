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

    public StudentDto getByName(String name) {
        StudentEntity entity = get(name);

        if (entity == null) {
            throw new AppBadRequestException("No student with this name was found: " + name);
        }
        return toDTO(entity);
    }
    public StudentEntity get(String name) {
        Optional<StudentEntity> optional = studentRepository.findByName(name);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No student with this name was found: " + name);
        }
        return optional.get();
    }

    public StudentDto getBySurname(String surname) {
        StudentEntity entity = getS(surname);
        if (entity == null) {
            throw new AppBadRequestException("No student with this surname was found: " + surname);
        }
        return toDTO(entity);
    }
    public StudentEntity getS(String surname) {
        Optional<StudentEntity> optional = studentRepository.findBySurname(surname);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No student with this surname was found: " + surname);
        }
        return optional.get();
    }

    public StudentDto getByLevel(Integer level) {
        StudentEntity entity = getL(level);
        if(entity == null) {
            throw new AppBadRequestException("No student with this level was found " + level);
        }
        return toDTO(entity);
    }
    private StudentEntity getL(Integer level) {
        Optional<StudentEntity> optional = studentRepository.findByLevel(level);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No student with this level was found: " + level);
        }
        return optional.get();
    }

    public StudentDto getByAge(Integer age) {
        StudentEntity entity = getA(age);
        if (entity == null) {
            throw new AppBadRequestException("No student with this age was found: " + age);
        }
        return toDTO(entity);
    }
    private StudentEntity getA(Integer age) {
        Optional<StudentEntity> optional = studentRepository.findByAge(age);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No student with this age was found: " + age);
        }
        return optional.get();
    }

    public StudentDto getByGender(String gender) {
        StudentEntity entity = getG(gender);
        if (entity == null) {
            throw new AppBadRequestException("No student with this gender was found: " + gender);
        }
        return toDTO(entity);

    }
    private StudentEntity getG(String gender) {
        Optional<StudentEntity> optional = studentRepository.findByGender(gender);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No student with this gender was found: " + gender);
        }
        return optional.get();
    }
}
