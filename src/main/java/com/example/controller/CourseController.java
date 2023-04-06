package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.StudentDto;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDto courseDto) {
        CourseDto response = courseService.create(courseDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.getById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable ("id") Integer id,
                                              @RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseService.updateById(id, courseDto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(courseService.deleteById(id));
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable ("name") String name) {
        return ResponseEntity.ok(courseService.getByName(name));
    }

    @GetMapping(value = "/getByPrice/{price}")
    public ResponseEntity<?> getByPrice(@PathVariable ("price") Double price) {
        return ResponseEntity.ok(courseService.getByPrice(price));
    }

    @GetMapping(value = "/getByDuration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable ("duration") String duration) {
        return ResponseEntity.ok(courseService.getByDuration(duration));
    }

    /*@GetMapping(value = "/getCourseListPriceBetween/{ptices}/{prices}")
    public ResponseEntity<?> getCourseListPriceBetween(@PathVariable {"price"}, {"price"} Double price) {
        return ResponseEntity.ok(courseService.getByCourseListPriceBetween(price, price));
    }*/

    /*@GetMapping(value = "/getCourseListCreatedDatesBetween/{date}/{date}")
    public ResponseEntity<?> getCourseListCreatedDateBetween(@PathVariable {"date"}, {"date"} LocalDateTime date) {
        return ResponseEntity.ok(courseService.getByCourseListCreatedDateBetween(date, date));
    }*/

}
