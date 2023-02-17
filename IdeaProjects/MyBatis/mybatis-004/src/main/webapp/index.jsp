<%@page contentType="text/html; charset=UTF-8" %>
<html>
<head>Bank Transfer</head>
<body>
<form action="/bank/transfer" method="post">
    转出账号：<input type="text" name="fromAct"><br>
    转入账号：<input type="text" name="toAct"><br>
    转账金额：<input type="text" name="money"><br>
    <input type="submit" value="转账">
</form>
</body>
</html>
