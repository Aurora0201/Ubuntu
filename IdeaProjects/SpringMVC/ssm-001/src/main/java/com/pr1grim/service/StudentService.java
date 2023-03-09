package com.pr1grim.service;

import com.pr1grim.pojo.Student;

import java.util.List;

public interface StudentService {
    /**
     * add student
     * @param student student
     */
    void addStudent(Student student);

    Student findOneById(Integer id);

    List<Student> findAllStudent();
}
