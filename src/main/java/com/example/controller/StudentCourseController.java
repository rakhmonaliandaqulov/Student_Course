package com.example.controller;

import com.example.dto.StudentCourseDetailDto;
import com.example.dto.StudentCourseDto;
import com.example.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/student_course")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

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


}
