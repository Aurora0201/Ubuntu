package com.emotion.analysis.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet({"/scan", "/getQR"})
public class AndroidServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
//        System.out.println(path);
        if("/scan".equals(path)){
            try {
                doScan(request, response);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else if("/getQR".equals(path)){
            getQR(request, response);
        }
    }

    private void getQR(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("getQR");
        response.sendRedirect("login.html");
    }


    protected void doScan(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("doScan done!");
//        Process exec = Runtime.getRuntime().exec("python3 /home/binjunkai/repo/IdeaProjects/EmotionAnalysis-VM/src/main/webapp/static/reptile.py");
//        Thread.sleep(20000);
        response.sendRedirect("login.html");
        /*BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            out.print(line);
        }*/
    }
}
