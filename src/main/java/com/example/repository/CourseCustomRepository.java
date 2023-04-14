package com.example.repository;

import com.example.dto.CourseFilterRequestDto;
import com.example.dto.StudentFilterRequestDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class CourseCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CourseEntity> getAll() {
        Query query = this.entityManager.createQuery("select s from CourseEntity as s");
        List<CourseEntity> studentList = query.getResultList();
        return studentList;
    }
    public List<CourseEntity> filter(CourseFilterRequestDto filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From CourseEntity as s where visible = true");

        if (filterDTO.getName() != null) {
            builder.append(" and s.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getPrice() != null) {
            builder.append(" and s.price = :price");
            params.put("price", filterDTO.getPrice());
        }
        if (filterDTO.getDuration() != null) {
            builder.append(" and s.duration = :duration");
            params.put("duration", filterDTO.getDuration());
        }
        if (filterDTO.getCreatedDate() != null) {
            builder.append(" and s.created_date = :created_date");
            params.put("created_date", filterDTO.getCreatedDate());
        }
        if (filterDTO.getFromDate() != null) {
            builder.append(" and s.gender = :gender");
            params.put("gender", filterDTO.getFromDate());
        }
        if (filterDTO.getToDate() != null) {
            builder.append(" and s.birth_date = :birth_date");
            params.put("birth_date", filterDTO.getToDate());
        }
        // ....
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List courseList = query.getResultList();
        return courseList;
    }
}
