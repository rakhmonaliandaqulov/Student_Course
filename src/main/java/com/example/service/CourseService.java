package com.example.service;

import com.example.dto.CourseDto;
import com.example.dto.StudentDto;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.hibernate.resource.transaction.backend.jdbc.spi.JdbcResourceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDto create(CourseDto dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());

        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
    public List<CourseDto> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDto> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }
    public CourseDto getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public List<CourseDto> toDTO(List<CourseEntity> entityList){
        List<CourseDto> dtoList = new LinkedList<>();
        for (CourseEntity entity : entityList) {
        CourseDto dto = new CourseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        dtoList.add(dto);
        }
        return dtoList;
    }
    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + id);
        }
        return optional.get();
    }
    public Boolean updateById(Integer id, CourseDto courseDto) {
       CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }

        entity.setName(courseDto.getName());
        entity.setPrice(courseDto.getPrice());
        entity.setDuration(courseDto.getDuration());
        entity.setCreatedDate(courseDto.getCreatedDate());

        courseRepository.save(entity);
        return true;
    }
    public Boolean deleteById(Integer id) {
        CourseEntity entity = get(id);

        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        courseRepository.delete(entity);
        return true;
    }

    public List<CourseDto> getByName(String name) {
        List<CourseEntity> list = courseRepository.findByName(name);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this name: " + name);
        }
        return toDTO(list);
    }
    public List<CourseDto> getByPrice(Double price) {
        List<CourseEntity> list = courseRepository.findByPrice(price);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this price: " + price);
        }
        return toDTO(list);
    }
    public List<CourseDto> getByDuration(String duration) {
        List<CourseEntity> list = courseRepository.findByDuration(duration);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this duration: " + duration);
        }
        return toDTO(list);
    }

    public List<CourseDto> getByCourseListPriceBetween(Double price1, Double price2) {
        List<CourseEntity> list = courseRepository.findByPriceBetween(price1, price2);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this prices between: ");
        }
        return toDTO(list);
    }

    public List<CourseDto> getByCourseListCreatedDateBetween(LocalDate date1, LocalDate date2) {
        List<CourseEntity> list = courseRepository.findByCreatedDateBetween(date1, date2);
        if (list.isEmpty()) {
            throw new AppBadRequestException("No course found with this createdDate between: ");
        }
        return toDTO(list);
    }

    public Page<CourseDto> paginationById(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDto> response = new PageImpl<CourseDto>(dtoList, pageable, totalCount);
        return response;
    }
    public Page<CourseDto> paginationByCreatedDate(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAll(pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDto> response = new PageImpl<CourseDto>(dtoList, pageable, totalCount);
        return response;

    }

    public Page<CourseDto> pagingByPriceWithCreatedDate(Double price, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findAllByPrice(price, pageable);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

        for (CourseEntity entity : entityList) {
            CourseDto dto = new CourseDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CourseDto> response = new PageImpl<CourseDto>(dtoList, pageable, totalCount);
        return response;
    }

    /*public Page<CourseDto> pagingByPricesWithCreateDateBetween(LocalDate date1, LocalDate date2, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Pageable pageable1 = PageRequest.of(page - 1, size, sort);

        Page<CourseEntity> page1 = courseRepository.findByCreatedDateBetween(date1, date2, pageable, pageable1);
        Long totalCount = page1.getTotalElements();
        List<CourseEntity> entityList = page1.getContent();
        List<CourseDto> dtoList = new LinkedList<>();

    }*/
}
/*
Student
  id,name,surname,level,age,Gender,createdDate
  1. Create student
  2. Get Student List. return all.
  3. Get student by id.
  4. Update student.
  5. Delete Student by id.
  6. Get method by each field. (getByName, getBySurname, getByLevel, getByAge, getByGender)
  7. Get StudentList by Given Date.
  8. Get StudentList  between given dates.
  9. Student Pagination;
  10. Student Pagination by given Level. List should be sorted by id.
  11. Pagination by given gender.  List should be sorted by createdDate.

Course
  id,name,price,duration,createdDate
  1. Create Course
  2. Get Course by id.
  3. Get Course list. return all.
  4. Update Course.
  5. Delete Course
  6. Get method by each field. (getByName, getByPrice,getByDuration)
  7. Get Course list between given 2 prices.
  8. Get Course list between 2 createdDates
  9. Course Pagination.
  10. Course Pagination. List should be sorted by createdDate.
  11. Course Pagination by price. List should be sorted by createdDate.
  12.  Course Pagination by price between. List should be sorted by createdDate.

StudentCourseMark
  id,studentId,courseId,mark,createdDate,
  1. Create StudentCourseMark
  2. Update StudentCourseMark
  3. Get StudentCourseMark by id.
      response (id,studentId,courseId,mark,createdDate,)
  4. Get StudentCourseMark by id with detail.
          response (id,student(id,name,surname),Course(id,name),mark,createdDate,)
        5. Delete StudentCourseMark by id.
        6. Get List of StudentCourseMark. Return All
  7. Studentni berilgan kundagi olgan baxosi
  8. Studentni berilgan 2kun oralig'idagi olgan baxosi.
  9. Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.
  10. Studentni berilgan curse dan olgan baxolari vaqt boyicha kamayish tartibida sord qiling.
  11. Studentni eng oxirda olgan baxosi, va curse nomi.
  12. Studentni olgan eng katta 3ta baxosi.
  13. Studentni eng birinchi olgan baxosi.
  14. Studenti eng berilgan course dan olgan birinchi baxosi.
  15. Studentni berilgan cuorse dan olgan eng baland baxosi.
  16. Studentni o'rtacha olgan baxolari.
  17. Studentni berilgan curse dan olgan o'rtacha baxolari.
  18. Studentni berilgan baxodan katta bo'lgan baxolari soni.
  19. Berilgan Cursdan eng baland baxo.
  20. Berilgan Cursdan o'lingan o'rtacha baxo.
  21. Berilgan Course dan olingna baxolar soni.
  22. StudentCourseMark pagination.
  23. StudentCourseMark pagination by given studentId. List should be sorted by createdDate.
  24. StudentCourseMark pagination by given courseId.  List should be sorted by createdDate.
  */
