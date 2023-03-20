package top.pi1grim.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import top.pi1grim.model.User;
import top.pi1grim.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/user/query")
    @ResponseBody
    public String query(Integer id) {
        return userService.findUserById(id).toString();
    }

    @RequestMapping("/user/add")
    @ResponseBody
    public void add(User user) {
        userService.addUser(user);
    }


    @RequestMapping("/user/test")
    public String test(Model model) {
        model.addAttribute("mail", "1173@qq.com");
        return "url";
    }

    @RequestMapping("/user/render")
    public void render(HttpServletResponse response) throws IOException {

    }
}
