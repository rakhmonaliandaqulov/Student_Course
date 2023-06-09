package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class CourseDto {
    private Integer id;
    private String name;
    private Double price;
    private String duration;
    private LocalDate createdDate;

}
