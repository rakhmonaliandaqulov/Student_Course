package com.example.repository;

import com.example.dto.CourseFilterRequestDto;
import com.example.dto.StudentCourseFilterRequestDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCourseCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentCourseEntity> getAll() {
        Query query = this.entityManager.createQuery("select s from StudentCourseEntity as s");
        List<StudentCourseEntity> studentList = query.getResultList();
        return studentList;
    }
    public List<StudentCourseEntity> filter(StudentCourseFilterRequestDto filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From StudentCourseEntity as s where visible = true");

        if (filterDTO.getStudentId() != null) {
            builder.append(" and s.student_id = :student_id");
            params.put("student_id", filterDTO.getStudentId());
        }
        if (filterDTO.getCourseId() != null) {
            builder.append(" and s.course_id = :course_id");
            params.put("course_id", filterDTO.getCourseId());
        }
        if (filterDTO.getMark() != null) {
            builder.append(" and s.mark = :mark");
            params.put("mark", filterDTO.getMark());
        }
        if (filterDTO.getCreatedDate() != null) {
            builder.append(" and s.created_date = :created_date");
            params.put("created_date", filterDTO.getCreatedDate());
        }
        if (filterDTO.getFromDate() != null) { // TODO
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
