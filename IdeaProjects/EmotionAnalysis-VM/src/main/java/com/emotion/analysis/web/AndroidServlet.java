package com.emotion.analysis.web;

import com.emotion.analysis.util.AndroidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Arrays;

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
        response.setContentType("application/octet-stream");
        ServletOutputStream out = response.getOutputStream();
//        File file = AndroidUtil.QR.png();
        FileInputStream fis = new FileInputStream("QR.png");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[2<<10];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        out.write(bos.toByteArray());
    }


    protected void doScan(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");

    }
}
