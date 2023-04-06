package com.example.service;

import com.example.dto.CourseDto;
import com.example.dto.StudentDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.hibernate.resource.transaction.backend.jdbc.spi.JdbcResourceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDto create(CourseDto dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public List<CourseDto> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDto> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }
    public CourseDto getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public List<CourseDto> toDTO(List<CourseEntity> entityList){
        List<CourseDto> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        dtoList.add(dto);
        }
        return dtoList;
    }
    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }
    public Boolean updateById(Integer id, CourseDto courseDto) {
       CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }

        entity.setName(courseDto.getName());
        entity.setPrice(courseDto.getPrice());
        entity.setDuration(courseDto.getDuration());
        entity.setCreatedDate(courseDto.getCreatedDate());

        courseRepository.save(entity);
        return true;
    }
    public Boolean deleteById(Integer id) {
        CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDto> getByName(String name) {
        List<CourseEntity> list = courseRepository.findByName(name);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this name: " + name);
        }
        return toDTO(list);
    }
    public List<CourseDto> getByPrice(Double price) {
        List<CourseEntity> list = courseRepository.findByPrice(price);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this price: " + price);
        }
        return toDTO(list);
    }
    public List<CourseDto> getByDuration(String duration) {
        List<CourseEntity> list = courseRepository.findByDuration(duration);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this duration: " + duration);
        }
        return toDTO(list);
    }
}
