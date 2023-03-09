package com.pr1grim.controller;

import com.pr1grim.pojo.Student;
import com.pr1grim.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class StudentController {
//    @Resource(name = "studentServiceImpl")
//    private StudentService service;
    @RequestMapping("/add")
    public void addStudent(Student student) {
        System.out.println(student);
//        service.addStudent(student);
    }
}
