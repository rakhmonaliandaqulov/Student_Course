package com.example.service;

import com.example.dto.StudentDto;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        for (StudentEntity entity : iterable) {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setBirthDate(entity.getBirthDate());
            dto.setGender(entity.getGender());

            dtoList.add(dto);
        };
        return dtoList;
    }

    public StudentDto getById(Integer id) {
        StudentEntity entity = get(id);
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
    public List<StudentDto> toDTO(List<StudentEntity> list){
        List<StudentDto> dtos = new LinkedList<>();
        for(StudentEntity entity : list){
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        dtos.add(dto);
        }
        return dtos;
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

    public List<StudentDto> getByName(String name) {
        List<StudentEntity> list = studentRepository.findByName(name);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this name was found: " + name);
        }
        return toDTO(list);
    }
    public List<StudentDto> getBySurname(String surname) {
        List<StudentEntity> list = studentRepository.findBySurname(surname);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this surname was found: " + surname);
        }
        return toDTO(list);
    }
    public List<StudentDto> getByLevel(Integer level) {
        List<StudentEntity> list = studentRepository.findByLevel(level);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this level was found: " + level);
        }
        return toDTO(list);
    }
    public List<StudentDto> getByAge(Integer age) {
        List<StudentEntity> list = studentRepository.findByAge(age);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this age was found: " + age);
        }
        return toDTO(list);
    }
    public List<StudentDto> getByGender(String gender) {
        List<StudentEntity> list = studentRepository.findByGender(StudentGender.valueOf(gender));
        if (list.isEmpty()) {
            throw new AppBadRequestException("No student with this gender was found: " + gender);
        }
        return toDTO(list);
    }
    public List<StudentDto> getByDate(LocalDate date) {
        List<StudentEntity> list = studentRepository.findByBirthDate(date);
        if (list.isEmpty()){
            throw new AppBadRequestException("No student with this gender was found: " + date);
        }
        return toDTO(list);
    }

    public List<StudentDto> getByBirthDateDateBetween(LocalDate date1, LocalDate date2) {
        List<StudentEntity> list = studentRepository.findByBirthDateBetween(date1, date2);
        if (list.isEmpty()){
            throw new AppBadRequestException("No student with this gender was found: ");
        }
        return toDTO(list);
    }

    public Page<StudentDto> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll(paging);

        Long totalCount = pageObj.getTotalElements();

        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDto> dtoList = new LinkedList<>();

        for (StudentEntity entity : entityList) {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }

        Page<StudentDto> response = new PageImpl<StudentDto>(dtoList, paging, totalCount);
        return response;
    }

    public Page<StudentDto> paginationWithName(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByName(name, paging);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDto> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDto dto = new StudentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dtoList.add(dto);
        }
        Page<StudentDto> response = new PageImpl<StudentDto>(dtoList, paging, totalCount);
        return response;
    }
}
