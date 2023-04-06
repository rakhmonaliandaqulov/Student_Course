package com.example.controller;

import com.example.dto.StudentDto;
import com.example.dto.StudentUpdateDTO;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.repository.StudentRepository;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.create(studentDto));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentDto>> getAll() {
       List<StudentDto> list = studentService.getAll();
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable ("id") Integer id,
                                              @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.updateById(id, studentDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(studentService.deleteById(id));
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable ("name") String name) {
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping(value = "/getBySurname/{surname}")
    public ResponseEntity<?> getBySurname(@PathVariable ("surname") String surname) {
        return ResponseEntity.ok(studentService.getBySurname(surname));
    }

    @GetMapping(value = "/getByLevel/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable ("level") Integer level) {
        return ResponseEntity.ok(studentService.getByLevel(level));
    }

    @GetMapping(value = "/getByAge/{age}")
    public ResponseEntity<?> getByAge(@PathVariable ("age") Integer age) {
        return ResponseEntity.ok(studentService.getByAge(age));
    }

    @GetMapping(value = "/getByGender/{gender}")
    public ResponseEntity<?> getByGender(@PathVariable ("gender") String gender) {
        return ResponseEntity.ok(studentService.getByGender(gender));
    }

    @GetMapping(value = "/getByGivenDate/{date}")
    public ResponseEntity<?> getByGivenDate(@PathVariable ("date") LocalDate date) {
        List<StudentDto> list = studentService.getByDate(date);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getByBirthDateBetween/{date1}/{date2}")
    public ResponseEntity<?> getByGivenDateBetween(@PathVariable ("date1") LocalDate date1,
                                                   @PathVariable ("date2") LocalDate date2) {
        return ResponseEntity.ok(studentService.getByBirthDateDateBetween(date1, date2));
    }
}
