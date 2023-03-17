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
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //模板所在目录，从resources目录开始的相对路径。
        resolver.setPrefix("/static/templates");
        //模板文件后缀
        resolver.setSuffix(".html");
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);

        //构造上下文(Model)
        List<String> array = new ArrayList<>();
        array.add("AA");
        array.add("BB");
        array.add("CC");
        Context context = new Context();
        context.setVariable("name", "工程名称");
        context.setVariable("array", array);

        //渲染模板
        PrintWriter out = response.getWriter();
        engine.process("auth_mailbox",context,out);

    }
}
