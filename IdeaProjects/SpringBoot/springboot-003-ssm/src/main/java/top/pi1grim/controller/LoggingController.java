package top.pi1grim.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.pi1grim.model.User;
import top.pi1grim.service.UserService;

@RestController
@RequestMapping("/api")
public class LoggingController {
    @Resource
    private UserService userService;
    @GetMapping("/log")
    public void log(String msg) {
        System.out.println(msg);
    }

    @PostMapping("/getRes")
    public void get(User user) {
        System.out.println(user);
        userService.addUser(user);
    }
}
