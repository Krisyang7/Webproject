<%@ page import="com.example.webproject.Student" %>
<html>
<head>
    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>A Simple Exam</title>
    <style type="text/css">
        h3, p {text-indent:2em;font-size:85%}
    </style>
</head>
<body>
<p>学号：<%= ((Student)application.getAttribute("user")).getSno()%></p>
<p>姓名：<%= ((Student)application.getAttribute("user")).getName()%></p>
<a href="Login.jsp">返回用户信息输入页面</a>
</body>
</html>

