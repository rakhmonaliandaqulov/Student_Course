package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.sql.results.graph.Fetch;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "student_course")
public class StudentCourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private StudentEntity studentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private CourseEntity courseId;

    @Column(name = "mark")
    private String mark;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
