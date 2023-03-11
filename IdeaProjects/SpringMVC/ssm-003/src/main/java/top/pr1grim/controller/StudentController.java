package top.pr1grim.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.pr1grim.bean.Student;
import top.pr1grim.exception.NameErrorException;
import top.pr1grim.exception.UserException;
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

    @RequestMapping("/c.do")
    public ModelAndView forwardTest() throws UserException {
        ModelAndView mv = new ModelAndView();
        if (true) {
            throw new NameErrorException("Illegal Name");
        }
        return mv;
    }

    @RequestMapping("/d.do")
    public ModelAndView redirectTest() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:forward.html");
        return mv;
    }

}
