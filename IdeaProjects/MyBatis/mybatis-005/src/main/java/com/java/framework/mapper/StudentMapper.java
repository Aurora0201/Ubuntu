package com.java.framework.mapper;

import com.java.framework.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StudentMapper {
    List<Student> selectById(Long id);

    List<Student> selectByName(String name);

    List<Student> selectByBirth(Date birth);

    List<Student> selectBySex(Character sex);
}
