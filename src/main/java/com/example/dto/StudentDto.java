package com.example.dto;

import com.example.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentDto {
    private Integer id;
    private String name;
    private String surname;
    private Integer level;
    private Integer age;
    private StudentGender gender;
    private LocalDate birthDAte;
}
