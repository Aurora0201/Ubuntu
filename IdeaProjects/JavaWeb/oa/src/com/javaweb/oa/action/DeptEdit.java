package com.javaweb.oa.action;

import com.javaweb.oa.Utils.JDBCUtils;
import com.mysql.cj.protocol.Resultset;
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

public class DeptEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String deptno = request.getParameter("deptno");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>Edit</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<form action='" + request.getContextPath() + "/dept/edit' method='post'>");

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(deptno));
            rs = ps.executeQuery();
            //Print the source table

            if(rs.next()){
                out.print("                deptno<input type='text' name='deptno' value='"+rs.getString("deptno")+"' readonly><br>");
                out.print("                dname<input type='text' name='dname' value='"+rs.getString("dname")+"'><br>");
                out.print("                loc<input type='text' name='loc' value='"+rs.getString("loc")+"'><br>");
            }

            out.print("    <input type='submit' value='Edit'>");
            out.print("</form>");
            out.print("</body>");
            out.print("</html>");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        int count;

        try {
            conn = JDBCUtils.getConnection();
            String sql = "update DEPT set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3,deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
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

