
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
${sessionScope.message}<br>
<form action="login.do" method="post">
    用户信息输入：<br>
    学号:<input type="text" name="id"/><br>
    密码:<input type="text" name="password"/><br>
    <input type="submit" value="确定"/>
</form>
</body>
</html>
