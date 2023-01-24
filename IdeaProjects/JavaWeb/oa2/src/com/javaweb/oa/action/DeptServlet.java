package com.javaweb.oa.action;

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

@WebServlet({"/dept/list", "/dept/add", "/dept/edit","/dept/delete","/dept/detail","/dept/modify"})
/*
    Also we can use like
    @WebServlet("/dept/*")
    but, the getServletPath() will only return "/dept"
*/

public class DeptServlet extends HttpServlet {
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
        }

    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
        out.print("<form action='" + request.getContextPath() + "/dept/modify' method='post'>");

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

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int count;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into DEPT values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, null);
        }
        if (count == 1) {
            request.getRequestDispatcher("/dept/list").forward(request,response);
        }else{
            request.getRequestDispatcher("/dept/error").forward(request,response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        response.setContentType("text/html");
        String contextPath = request.getContextPath();
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>");
        out.print("<html lang='en'>");
        out.print("<head>");
        out.print("    <meta charset='UTF-8'>");
        out.print("    <title>List</title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<script type='text/javascript'> ");
        out.print("function del(no) {");
        out.print("    if (window.confirm('Are you sure to delete the item?')) {");
        out.print("        alert('deleting please wait a second...');");
        out.print("        document.location ='" + contextPath + "/dept/delete?deptno=' + no");
        out.print("    }");
        out.print("}");
        out.print("</script>");
        out.print("<h1 align='center'>Department</h1>");
        out.print("<hr/>");
        out.print("<table align='center' border='2px' width='50%'>");
        out.print("  <tr>");
        out.print("    <th>Deptno</th>");
        out.print("    <th>Dname</th>");
        out.print("    <th>Loc</th>");
        out.print("    <th>Operation</th>");
        out.print("  </tr>");

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from DEPT";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                out.print("  <tr>");
                out.print("    <td>"+ deptno +"</td>");
                out.print("    <td>"+rs.getString("dname")+"</td>");
                out.print("    <td>"+rs.getString("loc")+"</td>");
                out.print("    <td>");
                out.print("      <a href='javascript:void(0)' onclick='del(" + deptno + ")')'>Delete</a>");
                out.print("      <a href='" + contextPath + "/dept/edit?deptno=" + deptno + "'>Edit</a>");
                out.print("      <a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>Details</a>");
                out.print("    </td>");
                out.print("  </tr>");
            }
            out.print("  <tr>");
            out.print("    <td colspan='4'><a href='" + contextPath + "/add.html'>Add item</a></td>");
            out.print("  </tr>");
            out.print("</table>");
            out.print("<hr>");
            out.print("</body>");
            out.print("</html>");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtils.close(conn, ps, rs);
        }
    }
}
