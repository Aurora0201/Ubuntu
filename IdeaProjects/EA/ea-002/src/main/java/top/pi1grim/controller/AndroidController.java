package top.pi1grim.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.pi1grim.service.AndroidService;
import top.pi1grim.util.AndroidUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class AndroidController {
    @Resource
    private AndroidService service;
    @RequestMapping("/getQR")
    public void getQR(HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // 允许某个
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有
        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();
        out.write(service.getCode());
    }

    @RequestMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
//        response.setContentType("text/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
        String json = "{res:";

        if (service.detectLogin())response.sendRedirect("/ea_002/transmitData");
//        else out.print(json + "false}");
    }

    @RequestMapping("/transmitData")
    public String transmitData() {
        System.out.println("Step into crawl data");
        service.crawlData(5);
        return "view";
    }

    @RequestMapping("/predict")
    public void predict() throws IOException {
        service.invokePython();
    }

}
