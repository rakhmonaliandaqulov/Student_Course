package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {

    @Query("FROM StudentCourseEntity where studentId = :student and createdDate = :created_date " +
            "order by mark")
    StudentCourseEntity findByStudentIdAndCreatedDateOrderByMark(StudentEntity student, LocalDate created_date);

    List<StudentCourseEntity> findAllByStudentIdAndCreatedDateBetween(StudentEntity studentId, LocalDate fromDate, LocalDate toDate);

    List<StudentCourseEntity> findByStudentIdOrderByMarkDesc(StudentEntity student);

    List<StudentCourseEntity> findByStudentIdAndCourseIdOrderByCourseIdDescMark(StudentEntity student, CourseEntity course);

    StudentCourseEntity findFirstByStudentIdOrderByMarkAsc(StudentEntity student);

    StudentCourseEntity findFirstByStudentIdOrderByMark(StudentEntity student);

    StudentCourseEntity findFirstByStudentIdAndAndCourseIdOrderByMark(StudentEntity student, CourseEntity course);

    Integer countByCourseIdOrderByMark(CourseEntity course);
}
