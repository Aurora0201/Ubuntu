package top.pi1grim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerInterceptor;

@Controller
public class HelloSpringBoot {
    @RequestMapping("/context")
    @ResponseBody
    public String hello() {
        System.out.println("hello done!");
        return "hello SpringBoot!";
    }
}
