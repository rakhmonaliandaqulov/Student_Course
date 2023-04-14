package com.example.repository;

import com.example.dto.StudentDto;
import com.example.dto.StudentFilterRequestDto;
import com.example.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<StudentEntity> getAll() {
        Query query = this.entityManager.createQuery("select s from StudentEntity as s");
        List<StudentEntity> studentList = query.getResultList();
        return studentList;
    }
    /*public List<StudentEntity> filter(StudentFilterRequestDto filterDto) {
        StringBuilder builder = new StringBuilder();
        Query query = this.entityManager.createQuery("");
        return null;
    }*/
    public List<StudentEntity> filter(StudentFilterRequestDto filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select s From StudentEntity as s where visible = true");

        if (filterDTO.getName() != null) {
            builder.append(" and s.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getSurname() != null) {
            builder.append(" and s.surname = :surname");
            params.put("surname", filterDTO.getSurname());
        }
        // ....
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List studentList = query.getResultList();
        return studentList;
    }

}
