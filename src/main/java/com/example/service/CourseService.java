package com.example.service;

import com.example.dto.CourseDto;
import com.example.dto.StudentDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
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

        iterable.forEach(entity -> dtoList.add(toDTO(entity)));
        return dtoList;

    }
    public CourseDto getById(Integer id) {
        CourseEntity entity = get(id);
        return toDTO(entity);
    }
    public CourseDto toDTO(CourseEntity entity){
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
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

    public CourseDto getByName(String name) {
        CourseEntity entity = getN(name);
        if(entity == null) {
            throw new AppBadRequestException("No course found with this name: " + name);
        }
        return toDTO(entity);
    }

    private CourseEntity getN(String name) {
        Optional<CourseEntity> optional = courseRepository.findByName(name);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No course found with this name: " + name);
        }
        return optional.get();
    }

    public CourseDto getByPrice(Double price) {
        CourseEntity entity = getP(price);
        if (entity == null) {
            throw new AppBadRequestException("No course found with this name: " + price);
        }
        return toDTO(entity);
    }

    private CourseEntity getP(Double price) {
        Optional<CourseEntity> optional = courseRepository.findByPrice(price);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No course found with this price: " + price);
        }
        return optional.get();
    }


    public CourseDto getByDuration(String duration) {
        CourseEntity entity = getD(duration);
        if (entity == null) {
            throw new AppBadRequestException("No course found with this duration: " + duration);
        }
        return toDTO(entity);
    }

    private CourseEntity getD(String duration) {
        Optional<CourseEntity> optional = courseRepository.findByDuration(duration);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("No course found with this duration: " + duration);
        }
        return optional.get();
    }
}
