package com.pr1grim.service.impl;

import com.pr1grim.mapper.StudentMapper;
import com.pr1grim.pojo.Student;
import com.pr1grim.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class StudentServiceImpl implements StudentService {
    private StudentMapper mapper;
    @Override
    public void addStudent(Student student) {
        mapper.insert(student);
    }

    @Override
    public Student findOneById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Student> findAllStudent() {
        return mapper.selectAll();
    }
}
