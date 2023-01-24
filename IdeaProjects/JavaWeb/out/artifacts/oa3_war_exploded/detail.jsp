<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>Detail</title>
</head>

<%
    String deptno = (String) request.getAttribute("deptno");
    String dname = (String) request.getAttribute("dname");
    String loc = (String) request.getAttribute("loc");
%>

<body>
    <table>
        <tr>
            <td>Deptno</td>
            <td>Dname</td>
            <td>Loc</td>
        </tr>
        <tr>
            <td><%=deptno%></td>
            <td><%=dname%></td>
            <td><%=loc%></td>
        </tr>
    </table>
<hr>
<input value="Back" type='button' onclick='window.history.back()'/>
</body>
</html>