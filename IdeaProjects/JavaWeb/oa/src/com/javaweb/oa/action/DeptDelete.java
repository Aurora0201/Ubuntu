package com.javaweb.oa.action;

import com.javaweb.oa.Utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
@WebServlet(name = "delete", urlPatterns = {"/dept/delete", "/dept/delet1"},
initParams = {@WebInitParam(name = "name", value = "root"), @WebInitParam(name = "password",value = "root1234")})
public class DeptDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {

            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            count = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, rs);
        }
        if (count == 1) {
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else{
//            request.getRequestDispatcher("/dept/error").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
