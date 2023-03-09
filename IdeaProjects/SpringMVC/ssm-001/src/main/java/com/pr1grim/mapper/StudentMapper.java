package com.pr1grim.mapper;

import com.pr1grim.pojo.Student;

import java.util.List;

/**
 * student mapper
 * @author binjunkai
 */
public interface StudentMapper {
    /**
     * insert data
     */
    void insert(Student student);

    /**
     * select by id
     * @param id id
     */
    Student selectById(Integer id);

    /**
     * select all
     * @return list
     */
    List<Student> selectAll();
}
