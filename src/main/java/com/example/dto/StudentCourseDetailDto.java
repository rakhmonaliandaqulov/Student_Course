package com.example.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseDetailDto {
    //(id,student(id,name,surname),Course(id,name),mark,createdDate,
    private Integer id;
    private Integer studentId;
    private String studentName;
    private String studentSurname;
    private Integer courseId;
    private String courseName;
    private String mark;
    private LocalDateTime createdDate;
}
