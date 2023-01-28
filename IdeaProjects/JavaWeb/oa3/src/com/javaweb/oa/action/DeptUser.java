package com.javaweb.oa.action;

import com.javaweb.oa.Utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/dept/login","/dept/exit"})
public class DeptUser extends HttpServlet {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.equals("/dept/login")) {
            doLogin(request, response);
        } else if (servletPath.equals("/dept/exit")) {
            doExit(request, response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        try {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from t_user where userName = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String noLogin = request.getParameter("noLogin");
                //Write the "true" first to avoid NullPointerException
                if ("true".equals(noLogin)) {
                    Cookie cookie1 = new Cookie("userName", userName);
                    Cookie cookie2 = new Cookie("password",password);
                    cookie1.setMaxAge(60 * 60 * 24 * 10);
                    cookie2.setMaxAge(60 * 60 * 24 * 10);

                    cookie1.setPath(request.getContextPath());
                    cookie2.setPath(request.getContextPath());
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }
                session.setAttribute("userName",userName);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else response.sendRedirect(request.getContextPath() + "/loginFailed.jsp");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally{
            JDBCUtils.close(conn, ps, rs);
        }

    }
}
