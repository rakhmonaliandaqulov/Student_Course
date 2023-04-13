package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.StudentDto;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(value = "/getCourseListPriceBetween/{price1}/{price2}")
    public ResponseEntity<?> getCourseListPriceBetween(@PathVariable ("price1") Double price1,
                                                       @PathVariable ("price2") Double price2) {
        return ResponseEntity.ok(courseService.getByCourseListPriceBetween(price1, price2));
    }

    @GetMapping(value = "/getCourseListCreatedDatesBetween/{date1}/{date2}")
    public ResponseEntity<?> getCourseListCreatedDateBetween(@PathVariable ("date1") LocalDate date1,
                                                             @PathVariable ("date2") LocalDate date2) {
        return ResponseEntity.ok(courseService.getByCourseListCreatedDateBetween(date1, date2));
    }

    @GetMapping(value = "/pagingById")
    public ResponseEntity<Page<CourseDto>> pagingpagingById(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(courseService.paginationById(page, size));
    }

    @GetMapping(value = "/pagingByCreatedDate")
    public ResponseEntity<Page<CourseDto>> pagingpagingByCreatedDate(@RequestParam(value = "page", defaultValue = "2") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size) {
        return ResponseEntity.ok(courseService.paginationByCreatedDate(page, size));
    }

    @GetMapping(value = "/pagingByPriceWithCreatedDate/{price}")
    public ResponseEntity<Page<CourseDto>> pagingByPriceWithCreatedDate(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "2") int size,
                                                                     @PathVariable ("price") Double price) {
        return ResponseEntity.ok(courseService.pagingByPriceWithCreatedDate(price, page, size));
    }

    @GetMapping(value = "/pagingByPricesWithCreatedDatesBetween/{price1}/{price2}")
    public ResponseEntity<Page<CourseDto>> pagingByPricesWithCreatedDatesBetween(@RequestParam(value = "page", defaultValue = "1") int page,
                                                             @RequestParam(value = "size", defaultValue = "5") int size,
                                                             @PathVariable ("price1") LocalDate date1,
                                                             @PathVariable ("price2") LocalDate date2) {
        return ResponseEntity.ok(courseService.pagingByPricesWithCreateDateBetween(date1, date2, page, size));
    }


}
