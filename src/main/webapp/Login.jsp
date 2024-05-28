
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
${sessionScope.message}<br>
<form action="login.do" method="post">
<div class="login-form">
    <h1>登录</h1>
    <div class="input-field">
        <label for="userid">账号:</label>
        <input type="text" placeholder="请输入账号" id="userid" name="id">
    </div>
    <div class="input-field">
        <label for="password">密码:</label>
        <input type="password" placeholder="请输入密码" id="password" name="password">
    </div>
    <input type="submit" value="立即登录" >
</div>
    <%
        String passwordChanged = request.getParameter("passwordChanged");
        if (passwordChanged != null && passwordChanged.equals("true")) {
    %>
    <div class="success-message">
        密码修改成功！
    </div>
    <%
        }
    %>
    <%
        String login = request.getParameter("login");
        if (login != null && login.equals("false")) {
    %>
    <div class="error-message">
        账号或密码错误！
    </div>
    <%
        }
    %>
    <%
        String locked = request.getParameter("locked");
        if (locked != null && locked.equals("true")) {
    %>
    <div class="error-message">
        账号已锁定！
    </div>
    <%
        }
    %>
</form>
</body>
</html>
