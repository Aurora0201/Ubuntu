package top.pr1grim.mapper;

import top.pr1grim.bean.Student;

import java.util.List;

public interface StudentMapper {
    void insert(Student student);

    Student selectById(Integer id);

    List<Student> selectAll();
}
