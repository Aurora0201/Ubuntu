package top.pi1grim.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.pi1grim.util.AndroidUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
@WebServlet({"/get", "/isLogin", "/getData"})
public class AndroidWeb extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/get".equals(path)) {
            getQR(request, response);
        } else if ("/isLogin".equals(path)) {
            isLogin(request, response);
        } else if ("/getData".equals(path)) {
            getData(request, response);
        }
    }

    protected void getQR(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // 允许某个
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有
        response.setContentType("application/octet-stream");
        ByteArrayOutputStream bos = null;
        ServletOutputStream out = null;

        FileInputStream in = new FileInputStream(AndroidUtil.getQRFile());

        bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1 << 10];
        int len;

        while ((len = in.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }

        out = response.getOutputStream();
        out.write(bos.toByteArray());
    }

    public void isLogin(HttpServletRequest request, HttpServletResponse response){
        long waitingTime = 30;
        long begin = System.currentTimeMillis();
        try {
            while (true) {
                if (AndroidUtil.isLogin() || System.currentTimeMillis() - begin > waitingTime * 1000) {
                    break;
                }
                System.out.println("超时等待：" + (System.currentTimeMillis() - begin)/1000.0);
            }
            if(AndroidUtil.isLogin())response.sendRedirect("/ea_001/getData");
            else response.sendRedirect("error.html");
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //crawl data
        AndroidUtil.getData(5);
        System.out.println("getData done!");
        //invoke python
//        Process exec = Runtime.getRuntime().exec("");
    }

}
