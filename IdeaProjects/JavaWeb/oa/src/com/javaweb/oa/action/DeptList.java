package com.javaweb.oa.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.javaweb.oa.Utils.JDBCUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptList extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                out.print("      <a href='/oa/dept/edit?deptno=" + deptno + "'>Edit</a>");
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
