package com.example.controller;

import com.example.dto.StudentCourseDto;
import com.example.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping(value = "/crerate")
    public ResponseEntity<?> create(@RequestBody StudentCourseDto studentCourseDto) {
        return ResponseEntity.ok(studentCourseService.create(studentCourseDto));
    }
}
