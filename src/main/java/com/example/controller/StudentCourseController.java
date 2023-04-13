package com.example.controller;

import com.example.dto.CourseDto;
import com.example.dto.StudentCourseDetailDto;
import com.example.dto.StudentCourseDto;
import com.example.dto.StudentDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
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

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable ("id") Integer id) {
        return ResponseEntity.ok(studentCourseService.deleteById(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentCourseDto>> getAll() {
        return ResponseEntity.ok(studentCourseService.getAll());
    }

    @GetMapping(value = "/getByDate")
    public ResponseEntity<?> getByDate(@RequestParam("studentId") Integer id, @RequestParam("created_date") LocalDateTime created_date){
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseDto dto = studentCourseService.getByDate(student, created_date);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getBetweenDate")
    private ResponseEntity<List<StudentCourseDto>> getBetweenDate(@RequestParam("studentId") Integer sId,
                                                                  @RequestParam("fromDate") LocalDateTime fromDate,
                                                                  @RequestParam("toDate") LocalDateTime toDate){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseDto> dto = studentCourseService.
                getStudentCourseMarkListBetweenDates(student, fromDate, toDate);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getAllMark")
    private ResponseEntity<List<StudentCourseDto>> getAllMark(@RequestParam("studentId") Integer sId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        List<StudentCourseDto> dto = studentCourseService.getAllStudentMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getDateMark")
    private ResponseEntity<List<StudentCourseDto>> getAllMark(@RequestParam("studentId") Integer sId,
                                                              @RequestParam("courseId") Integer cId){
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        List<StudentCourseDto> dto = studentCourseService.getDateMark(student,course);
        return ResponseEntity.ok(dto);
    }
    @GetMapping(value = "/getTopMark/{id}")
    public ResponseEntity<?> getFirstMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseDto dto = studentCourseService.getFirstMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getLastMark/{id}")
    public ResponseEntity<?> getLastMark(@PathVariable("id") Integer id) {
        StudentEntity student = new StudentEntity();
        student.setId(id);
        StudentCourseDto dto = studentCourseService.getLastMark(student);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/getStudentCourseFirstMark")
    public ResponseEntity<?> getLastMark(@RequestParam("studentId") Integer sId,
                                         @RequestParam("courseId") Integer cId) {
        StudentEntity student = new StudentEntity();
        student.setId(sId);
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        StudentCourseDto dto = studentCourseService.getStudentCurseFirstMark(student, course);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/countCourseMark")
    public ResponseEntity<?> countCourseMark(@RequestParam("courseId") Integer cId) {
        CourseEntity course = new CourseEntity();
        course.setId(cId);
        Integer count = studentCourseService.countCourseMark( course);
        return ResponseEntity.ok(count);
    }
    @GetMapping(value = "/paging")
    public ResponseEntity<Page<StudentCourseDto>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(studentCourseService.pagination(page, size));
    }

    @GetMapping(value = "/paginationWithStudentId/{student_id}")
    public ResponseEntity<Page<StudentCourseDto>> paginationWithStudentId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                        @RequestParam(value = "size", defaultValue = "2") int size,
                                                                        @PathVariable ("student_id") Integer id) {
        return ResponseEntity.ok(studentCourseService.paginationWithStudentId(id, page, size));
    }

    @GetMapping(value = "/paginationWithCourseId/{course_id}")
    public ResponseEntity<Page<StudentCourseDto>> paginationWithCourseId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                            @RequestParam(value = "size", defaultValue = "2") int size,
                                                                            @PathVariable ("course_id") Integer id) {
        return ResponseEntity.ok(studentCourseService.paginationWithCourseId(id, page, size));
    }

    @PostMapping(value = "/paging-studentId")
    public ResponseEntity<Page<StudentCourseDto>> paginationWithStudentId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "30") int size,
                                                               @RequestBody StudentCourseDto filter) {
        return ResponseEntity.ok(studentCourseService.paginationWithStudentId(filter.getStudentId(), page, size));
    }
    @PostMapping(value = "/paging-courseId")
    public ResponseEntity<Page<StudentCourseDto>> paginationWithCourseId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                      @RequestParam(value = "size", defaultValue = "30") int size,
                                                                      @RequestBody StudentCourseDto filter) {
        return ResponseEntity.ok(studentCourseService.paginationWithCourseId(filter.getCourseId(), page, size));
    }

}
