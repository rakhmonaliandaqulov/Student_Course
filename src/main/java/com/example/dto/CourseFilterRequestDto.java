package com.example.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CourseFilterRequestDto {
    private Integer id;
    private String name;
    private Double price;
    private String duration;
    private LocalDate createdDate;
    private LocalDate fromDate;
    private LocalDate toDate;
}
