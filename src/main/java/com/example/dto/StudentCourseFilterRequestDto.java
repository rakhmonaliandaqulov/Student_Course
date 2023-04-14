package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class StudentCourseFilterRequestDto {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private String mark;
    private LocalDateTime createdDate;
    private LocalDate fromDate;
    private LocalDate toDate;
}
