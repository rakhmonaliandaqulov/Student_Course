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

        iterable.forEach(courseEntity -> {
            CourseDto dto = new CourseDto();
            dto.setId(courseEntity.getId());
            dto.setName(courseEntity.getName());
            dto.setPrice(courseEntity.getPrice());
            dto.setDuration(courseEntity.getDuration());
            dto.setCreatedDate(courseEntity.getCreatedDate());

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

        courseRepository.save(entity);
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
}
