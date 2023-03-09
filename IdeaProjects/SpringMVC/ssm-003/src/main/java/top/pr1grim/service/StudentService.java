package top.pr1grim.service;

import org.springframework.stereotype.Service;
import top.pr1grim.bean.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    Student findStudentById(Integer id);

    List<Student> showAllStudent();
}
