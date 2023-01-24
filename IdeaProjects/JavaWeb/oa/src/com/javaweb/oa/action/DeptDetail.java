package com.javaweb.oa.action;

import com.javaweb.oa.Utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String s = request.getParameter("deptno");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>Detail</title>");
        out.print("</head>");
        out.print("<body>");

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            rs = ps.executeQuery();
            if(rs.next())out.print(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3));
            out.print("<hr><br>");
            out.print("<input type='button' onclick='window.history.back()' value='Back'/>");
            out.print("</body>");
            out.print("</html>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, rs);
        }

    }
}
