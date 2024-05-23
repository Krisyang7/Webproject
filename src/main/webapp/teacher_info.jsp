<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Information</title>
    <link rel="stylesheet" href="operation.css">
</head>
<body>
<h1>在校信息</h1>

<%--<div class="`operation_page`">--%>
    <div class="top_bar">

        <a href="Login.jsp" style="color: white;text-decoration: none;">退出登录</a>
    </div>
<%--    <div class="side_bar">--%>
<%--        <ul>--%>
<%--            <li style="text-align: center">--%>
<%--                <h3 id="teacher_name_Display"></h3>--%>
<%--            </li>--%>


<%--<div class="topnav">--%>
<%--    <a href="basic_info.jsp">基础信息</a>--%>
<%--    <a href="admission_info.jsp">入学信息</a>--%>
<%--    <a href="extended_info.jsp">扩展信息</a>--%>
<%--</div>--%>
<table border="1">
    <tr>
        <td><b>教师编号:</b></td>
        <td>${teacher.id}</td>
    </tr>
    <tr>
        <td><b>姓名:</b></td>
        <td>${teacher.name}</td>
    </tr>
    <tr>
        <td><b>性别:</b></td>
        <td>${student.gender}</td>
    </tr>
    <tr>
        <td><b>学院:</b></td>
        <td>${student.academy}</td>
    </tr>
    <tr>
        <td><b>导师:</b></td>
        <td>${student.mentor}</td>
    </tr>
    <tr>
        <td><b>专业:</b></td>
        <td>${student.major}</td>
    </tr>
    <tr>
        <td><b>学位:</b></td>
        <td>${student.degree}</td>
    </tr>
    <tr>
        <td><b>电话:</b></td>
        <td>${student.phonenumber}</td>
    </tr>
    <tr>
        <td><b>Email:</b></td>
        <td>${student.email}</td>
    </tr>
    <tr>
        <td><b>地址:</b></td>
        <td>${student.address}</td>
    </tr>
    <tr>
        <td><b>籍贯:</b></td>
        <td>${student.nativePlace}</td>
    </tr>
    <tr>
        <td><b>火车起始站:</b></td>
        <td>${student.trainstart}</td>
    </tr>
    <tr>
        <td><b>火车终点站:</b></td>
        <td>${student.trainend}</td>
    </tr>
    <tr>
        <td><b>婚姻情况:</b></td>
        <td>${student.marrystatus}</td>
    </tr>
</table>
</body>

</html>
