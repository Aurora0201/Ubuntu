package top.pr1grim.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.pr1grim.bean.Student;
import top.pr1grim.service.StudentService;

@Controller
public class StudentController {
    @Resource
    private StudentService service;
    @RequestMapping("/a.do")
    public void addStudent(Student student) {
        System.out.println("123123");
        System.out.println(student);
        service.addStudent(student);
    }

    @RequestMapping("/b.do")
    public void findStudent(Integer id) {
        Student student = service.findStudentById(1);
        System.out.println(student);
    }
}
