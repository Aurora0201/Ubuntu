package com.javaweb.oa.action;

import com.javaweb.oa.Utils.JDBCUtils;
import com.javaweb.oa.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/welcome"})
public class DeptWelcome extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String userName = null;
        String password = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("userName".equals(name)) {
                    userName = cookie.getValue();
                }else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }
        if (userName != null && password != null) {
            try {
                conn = JDBCUtils.getConnection();
                String sql = "select * from t_user where username = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, userName);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", new User(userName,password));
                    response.sendRedirect(request.getContextPath() + "/dept/list");
                }else response.sendRedirect(request.getContextPath() + "/login.jsp");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
