package com.example.controller;

import com.example.dto.StudentDto;
import com.example.entity.StudentEntity;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto) {
        StudentDto response = studentService.create(studentDto);
        return ResponseEntity.ok(response);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentDto>> getAll() {
//        List<StudentDto> list = studentService.getAll();
        return ResponseEntity.ok(studentService.getAll());
    }
}
