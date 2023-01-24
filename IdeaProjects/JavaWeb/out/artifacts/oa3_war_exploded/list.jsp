<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List</title>
</head>
<body>
<h1 align="center">Department</h1>
<hr/>
<table align="center" border="2px" width="50%">
  <tr>
    <th>Deptno</th>
    <th>Dname</th>
    <th>Loc</th>
    <th>Operation</th>
  </tr>
  <tr>
    <td>123</td>
    <td>123</td>
    <td>123</td>
    <td>
      <a href="<%=request.getContextPath()%>/delete.html" onclick="del(20)">Delete</a>
      <a href="<%=request.getContextPath()%>/edit.html">Edit</a>
      <a href="<%=request.getContextPath()%>/detail.html">Details</a>
    </td>
  </tr>
</table>
<hr>
<a href="<%=request.getContextPath()%>/add.html">Add item</a>

</body>
</html>