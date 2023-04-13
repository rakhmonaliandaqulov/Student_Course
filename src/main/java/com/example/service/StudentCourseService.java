package com.example.service;

import com.example.dto.*;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.mapper.CourseInfoMapper;
import com.example.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public Integer create(StudentCourseDto dto) {
        StudentCourseEntity entity = new StudentCourseEntity();

        StudentEntity student = studentService.get(dto.getStudentId());
        if (student == null) {
            throw new AppBadRequestException("Student not found: " + dto.getStudentId());
        }
        CourseEntity course = courseService.get(dto.getCourseId());
        if (course == null) {
            throw new AppBadRequestException("Course not found: " + dto.getCourseId());
        }

        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());
        entity.setCreatedDate(LocalDateTime.now());

        studentCourseRepository.save(entity);
        dto.setId(entity.getId());
        return entity.getId();
    }

    public Boolean update(Integer id, StudentCourseDto dto) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setMark(dto.getMark());

        studentCourseRepository.save(entity);
        return true;
    }

    public StudentCourseEntity get1(Integer id) {
        Optional<StudentCourseEntity> optional = studentCourseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        return optional.get();
    }

    public StudentCourseDto getById(Integer id) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentCourseDetailDto> getByIdWithDetail(Integer id) {
        List<StudentCourseDetailDto> list = new LinkedList<>();

        studentCourseRepository.findAllById(Collections.singleton(id)).forEach(entity -> {
            StudentCourseDetailDto dto = new StudentCourseDetailDto();
            dto.setId(entity.getId());

            dto.setStudentId(entity.getStudentId());
            dto.setStudentName(entity.getStudent().getName());
            dto.setStudentSurname(entity.getStudent().getSurname());

            dto.setCourseId(entity.getCourseId());
            dto.setCourseName(entity.getCourse().getName());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            list.add(dto);
        });
        return list;
    }

    public Boolean deleteById(Integer id) {
        StudentCourseEntity entity = get1(id);
        if (entity == null) {
            throw new AppBadRequestException("StudentCourse not found: " + id);
        }
        studentCourseRepository.deleteById(id);
        return true;
    }

    public List<StudentCourseDto> getAll() {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAll();
        List<StudentCourseDto> dtoList = new LinkedList<>();

        for (StudentCourseEntity entity : iterable) {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());

            dtoList.add(dto);
        };
        return dtoList;
    }

    /*public List<StudentCourseDto> getByCreatedDateMark(LocalDate time, Integer id) {
        List<StudentCourseEntity> list = studentCourseRepository.findByCreatedDateAndStudentCourseEntityId(time, id);
        if (list.isEmpty()) {
            throw new AppBadRequestException("StudentCourse not found: " + time);
        }
        return toDTO(list);
    }*/
    public List<StudentCourseDto> toDTO(List<StudentCourseEntity> list){
        List<StudentCourseDto> dtos = new LinkedList<>();
        for(StudentCourseEntity entity : list){
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setMark(entity.getMark());
            dtos.add(dto);
        }
        return dtos;
    }

    public StudentCourseDto getByDate(StudentEntity student_id, LocalDateTime date) {
        StudentCourseEntity entity = studentCourseRepository.
                findByStudentIdAndCreatedDateOrderByMark(student_id, date);
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public List<StudentCourseDto> getStudentCourseMarkListBetweenDates(StudentEntity studentId, LocalDateTime fromDate, LocalDateTime toDate) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.
                findAllByStudentIdAndCreatedDateBetween(studentId, fromDate,toDate);
        List<StudentCourseDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseDto> getAllStudentMark(StudentEntity studentId) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.
                findByStudentIdOrderByMarkDesc(studentId);
        List<StudentCourseDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public List<StudentCourseDto> getDateMark(StudentEntity studentId, CourseEntity courseId) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.
                findByStudentIdAndCourseIdOrderByCourseIdDescMark(studentId, courseId);
        List<StudentCourseDto> studentDTOLinkedList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            studentDTOLinkedList.add(dto);
        });

        return studentDTOLinkedList;
    }

    public StudentCourseDto getFirstMark(StudentEntity id) {
        StudentCourseEntity entity = studentCourseRepository.findFirstByStudentIdOrderByMarkAsc(id);
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
    public StudentCourseDto getLastMark(StudentEntity id) {
        StudentCourseEntity entity = studentCourseRepository.findFirstByStudentIdOrderByMark(id);
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public StudentCourseDto getStudentCurseFirstMark(StudentEntity id, CourseEntity course) {
        StudentCourseEntity entity = studentCourseRepository.findFirstByStudentIdAndAndCourseIdOrderByMark(id,course);
        StudentCourseDto dto = new StudentCourseDto();
        dto.setId(entity.getId());
        dto.setCourseId(entity.getCourseId());
        dto.setStudentId(entity.getStudentId());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public Integer countCourseMark(CourseEntity course) {
        Integer count = studentCourseRepository.countByCourseIdOrderByMark(course);
        return count;
    }
    public void test() {
        List<Object[]> courseObjList = studentCourseRepository.findLastCourseMarkerAsNative(1);
        if (courseObjList.size() > 0) {
            Object[] courseObj = courseObjList.get(0);

            CourseDto courseDTO = new CourseDto();
            courseDTO.setId((Integer) courseObj[0]);
            courseDTO.setName((String) courseObj[1]);
            System.out.println(courseDTO);
        }

        System.out.println("dasda");
    }

    public void test2() {
        CourseInfoMapper courseInfoMapper = studentCourseRepository.findLastCourseMarkerAsNativeMapping(1);
        if (courseInfoMapper != null) {
            CourseDto courseDTO = new CourseDto();
            courseDTO.setId(courseInfoMapper.getCId());
            courseDTO.setName(courseInfoMapper.getCName());
            System.out.println(courseDTO +" "+ courseInfoMapper.getMark());
        }

        System.out.println("dasda");
    }

    public Page<StudentCourseDto> pagination(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseEntity> page1 = studentCourseRepository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<StudentCourseEntity> entityList = page1.getContent();
        List<StudentCourseDto> dtoList = new LinkedList<>();

        for (StudentCourseEntity entity : entityList) {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseDto> response = new PageImpl<StudentCourseDto>(dtoList, pageable, totalCount);
        return response;
    }
    public Page<StudentCourseDto> pagingByIdWithCreatedDate(StudentEntity id, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<StudentCourseEntity> page1 = studentCourseRepository.findAllById(id, pageable);
        Long totalCount = page1.getTotalElements();
        List<StudentCourseEntity> entityList = page1.getContent();
        List<StudentCourseDto> dtoList = new LinkedList<>();

        for (StudentCourseEntity entity : entityList) {
            StudentCourseDto dto = new StudentCourseDto();
            dto.setId(entity.getId());
            dto.setCourseId(entity.getCourseId());
            dto.setStudentId(entity.getStudentId());
            dto.setMark(entity.getMark());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<StudentCourseDto> response = new PageImpl<StudentCourseDto>(dtoList, pageable, totalCount);
        return response;
    }
}
