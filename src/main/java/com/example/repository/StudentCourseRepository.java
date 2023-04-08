package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {

    List<StudentCourseEntity> findAllById(Integer id);

    List<StudentCourseEntity> findByCreatedDate(LocalDateTime time);

    List<StudentCourseEntity> findByCreatedDateAndStudentCourseEntityId(LocalDate time, Integer id);

//    @Query("select * from StudentCourseEntity " +
//            "inner join StudentEntity on StudentCourseEntity.studentId = StudentEntity.id" +
//            "where createdDate = :time")
//    List<StudentCourseEntity> findByCreatedDateMark(@Param("time") LocalDateTime time);
}
/*

    List<StudentCourseEntity> findByCreatedDateBetweenAndStudentEntityId(LocalDate befor, LocalDate after, Integer id);
    List<StudentCourseEntity> findByStudentEntityIdOrderByCreatedDateDesc(Integer id);
    List<StudentCourseEntity> findByStudentEntityIdAndCourseEntityIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);
    List<StudentCourseEntity> findTop1ByStudentEntityIdOrderByCreatedDateDesc(Integer id);
    List<StudentCourseEntity> findTop3ByStudentEntityIdOrderByMarkDesc(Integer id);
    List<StudentCourseEntity> findTop1ByStudentEntityIdOrderByCreatedDate(Integer id);
    List<StudentCourseEntity> findTop1ByStudentEntityIdAndCourseEntityIdOrderByMarkDesc(Integer studentId, Integer courseId);
    */
