package com.java.framework.mapper;

import com.java.framework.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StudentMapper {
    List<Student> selectById(Long id);

    List<Student> selectByMultiCondition(@Param("name") String name, @Param("age") int age, @Param("sex") Character sex);

    void updateById(Student student);
}
