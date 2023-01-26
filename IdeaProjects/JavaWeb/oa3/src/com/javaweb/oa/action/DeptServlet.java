package com.javaweb.oa.action;

import com.javaweb.oa.bean.*;

import com.javaweb.oa.Utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet({"/dept/list", "/dept/add", "/dept/edit","/dept/delete","/dept/detail","/dept/modify","/dept/login"})
/*
    Also we can use like
    @WebServlet("/dept/*")
    but, the getServletPath() will only return "/dept"
*/
public class DeptServlet extends HttpServlet {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getServletPath();
        if (name.equals("/dept/list")) {
            doList(request,response);
        } else if (name.equals("/dept/add")) {
            doAdd(request,response);
        }else if (name.equals("/dept/edit")) {
            doEdit(request,response);
        }else if (name.equals("/dept/delete")) {
            doDel(request,response);
        }else if (name.equals("/dept/detail")) {
            doDetail(request,response);
        }else if (name.equals("/dept/modify")) {
            doModify(request,response);
        } else if (name.equals("/dept/login")) {
            doLogin(request, response);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
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
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else response.sendRedirect(request.getContextPath() + "/loginFailed.html");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        } finally{
            JDBCUtils.close(conn, ps, rs);
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        http://localhost:8080/oa/dept/detail?deptno=10
        String deptno = request.getParameter("deptno");
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                request.setAttribute("deptno", deptno);
                request.setAttribute("dname", dname);
                request.setAttribute("loc", loc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
        request.getRequestDispatcher("/detail.jsp").forward(request,response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
        int cot;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into DEPT value(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            cot = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,null);
        }
        response.sendRedirect(request.getContextPath()+(cot == 1?"/dept/list":"/error.html"));
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       List<Dept> depts = new ArrayList<>();
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                depts.add(new Dept(deptno,dname,loc));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
        request.setAttribute("depts",depts);
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                request.setAttribute("deptno", deptno);
                request.setAttribute("dname", dname);
                request.setAttribute("loc", loc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,rs);
        }
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        int cot;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from DEPT where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            cot = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,null);
        }
        response.sendRedirect(request.getContextPath()+(cot == 1?"/dept/list":"/error.html"));
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        int cot;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "update DEPT set dname = ?, loc = ? where deptno = " + deptno;
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2,loc);
            cot = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn,ps,null);
        }
        response.sendRedirect(request.getContextPath() + ((cot == 1)?"/dept/list":"error.html"));
    }


}
