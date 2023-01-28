<%@ page import="com.javaweb.oa.bean.Dept" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>DataSystem</title>
  <script>
    function del(no) {
      if (window.confirm("Are you sure to delete the data?")) {
        window.location = "/oa/dept/delete?deptno=" + no;
      }
    }
  </script>

</head>
<body>
<h1 align="center">Department</h1>
<h2>Welcome <%=session.getAttribute("username")%> !<a href="<%=request.getContextPath()%>/dept/exit">Log out</a></h2>
<hr/>
<table align="center" border="2px" width="50%">
  <tr>
    <th>Index</th>
    <th>Deptno</th>
    <th>Dname</th>
    <th>Operation</th>
  </tr>

    <%
      List<Dept> depts = (List<Dept>)request.getAttribute("depts");
      int cot = 0;
      for (Dept dept : depts) {
      cot++;
    %>
      <tr>
        <td><%=cot%></td>
        <td><%=dept.getDeptno()%></td>
        <td><%=dept.getDname()%></td>
        <td>
          <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">Delete</a>
          <a href="<%=request.getContextPath()%>/dept/edit?deptno=<%=dept.getDeptno()%>">Edit</a>
          <a href="<%=request.getContextPath()%>/dept/detail?deptno=<%=dept.getDeptno()%>">Details</a>
        </td>
      </tr>
    <%}%>


</table>
<hr>
  <a href="<%=request.getContextPath()%>/add.jsp">Add item</a>
</body>
</html>