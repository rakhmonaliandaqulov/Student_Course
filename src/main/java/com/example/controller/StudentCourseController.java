package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.StudentCourseDetailDto;
import com.example.dto.StudentCourseDto;
import com.example.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/student_course")
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseDto studentCourseDto) {
        return ResponseEntity.ok(studentCourseService.create(studentCourseDto));
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable ("id") Integer id,
                                    @RequestBody StudentCourseDto studentCourseDto) {
        return ResponseEntity.ok(studentCourseService.update(id, studentCourseDto));
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(studentCourseService.getById(id));
    }

    @GetMapping(value = "/getByIdWithDetail/{id}")
    public ResponseEntity<List<StudentCourseDetailDto>> getByIdWithDetail(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(studentCourseService.getByIdWithDetail(id));
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(studentCourseService.deleteById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentCourseDto>> getAll() {
        return ResponseEntity.ok(studentCourseService.getAll());
    }

    @GetMapping(value = "/student's_mark/{createdDate}")
    public ResponseEntity<List<StudentCourseDto>> getByCreatedDateMark(@PathVariable ("createdDate") LocalDateTime time) {
        return ResponseEntity.ok(studentCourseService.getByCreatedDateMark(time));
    }



}
