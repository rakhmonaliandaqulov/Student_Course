package com.example.dto;

import com.example.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class StudentUpdateDTO {
    private String name;
    private String surname;
    private Integer age;
    private LocalDate birthDate;
    private Integer level;
    private StudentGender gender;
}
