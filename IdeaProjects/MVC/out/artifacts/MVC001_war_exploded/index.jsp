<%--
  Created by IntelliJ IDEA.
  User: binjunkai
  Date: 2023/2/6
  Time: 下午4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>Transfer</title>
  </head>
  <body>
  <form action="transfer" method="post">
    From account<input type="text" name="fromAct"/><br>
    To account<input type="text" name="toAct"/><br>
    Transfer amount<input type="text" name="money"/><br>
    <input type="submit" value="Transfer">
  </form>
  </body>
</html>
