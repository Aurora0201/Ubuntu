package top.pr1grim.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.pr1grim.bean.Student;
import top.pr1grim.mapper.StudentMapper;
import top.pr1grim.service.StudentService;

import java.util.List;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper mapper;

    @Override
    public void addStudent(Student student) {
        mapper.insert(student);
    }

    @Override
    public Student findStudentById(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Student> showAllStudent() {
        return mapper.selectAll();
    }
}
