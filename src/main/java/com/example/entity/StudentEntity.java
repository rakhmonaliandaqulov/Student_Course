package com.example.entity;

import com.example.enums.StudentGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "level", nullable = false)
    private Integer level;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private StudentGender gender = StudentGender.FEMALE;
    @Column(name = "birth_date")
    private LocalDate birthDate;
}
