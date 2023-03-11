package top.pi1grim.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import top.pi1grim.util.DBUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/save", "/get"})
public class ImgServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/save".equals(path)){
            doSave(request, response);
        }else if("/get".equals(path)){
            doGet(request, response);
        }
    }
    public void doSave(HttpServletRequest request, HttpServletResponse response){
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/usr/local/tomcat10/webapps/qr/WEB-INF/classes/QR.png");
            conn = DBUtil.getConnection();
            String sql = "insert into t_img values(null, ?)";
            ps = conn.prepareStatement(sql);
            ps.setBinaryStream(1, fis, fis.available());
            int count = ps.executeUpdate();
            if (count == 1) {
                System.out.println("successful");
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }finally{
            DBUtil.close(conn, ps, null);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream in = null;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080"); // 允许某个
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有
        response.setContentType("application/octet-stream");
        ByteArrayOutputStream bos = null;
        ServletOutputStream out = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "select img from t_img where id = 1";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                in = rs.getBinaryStream(1);
            }
            byte[] buffer = new byte[1 << 10];
            int len;
            bos = new ByteArrayOutputStream();
            while ((len = in.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            out = response.getOutputStream();
            out.write(bos.toByteArray());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
