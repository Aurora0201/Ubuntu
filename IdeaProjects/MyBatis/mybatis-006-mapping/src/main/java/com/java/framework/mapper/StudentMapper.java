package com.java.framework.mapper;

import com.java.framework.bean.Student;

import java.util.List;

public interface StudentMapper {
    List<Student> selectById(Integer id);

    List<Student> selectByIdAssociate(Integer id);

    List<Student> selectByIdStep1(Integer id);

    List<Student> selectByIdStep2(Integer id);

}
