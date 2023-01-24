<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>

</head>
<%
    String deptno = (String) request.getAttribute("deptno");
    String dname = (String) request.getAttribute("dname");
    String loc = (String) request.getAttribute("loc");
%>
<body>
<form action="<%=request.getContextPath()%>/dept/modify" method="post">
    deptno<input type="text" name="deptno" value="<%=deptno%>" readonly><br>
    dname<input type="text" name="dname" value="<%=dname%>"><br>
    loc<input type="text" name="loc" value="<%=loc%>"><br>
    <input type="submit" value="Edit">
</form>
</body>
</html>