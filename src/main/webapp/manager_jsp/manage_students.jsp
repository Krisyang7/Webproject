<%@ page import="com.example.webproject.Bean.Student" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Students</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .top_bar {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: right;
        }
        .top_bar a {
            color: white;
            text-decoration: none;
            padding: 8px 16px;
            background: #007BFF;
            border-radius: 4px;
        }
        .form-input {
            margin: 10px 0;
        }
    </style>
</head>
<body>
<div class="top_bar">
    <a href="logout.jsp">退出登录</a>
</div>
<div class="container">
    <h1>学生信息管理</h1>
    <table>
        <thead>
        <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>身份证号</th>
            <th>学院</th>
            <th>专业</th>
            <th>学位类型</th>
            <th>导师</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            for (Student student : students) {
        %>
        <tr>
            <td><%= student.getName() %></td>
            <td><%= student.getGender() %></td>
            <td><%= student.getId() %></td>
            <td><%= student.getCollege() %></td>
            <td><%= student.getMajor() %></td>
            <td><%= student.getDegree() %></td>
            <td><%= student.getMentor() %></td>
            <td>
                <form action="edit_student.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="student_id" value="<%= student.getId() %>">
                    <input type="submit" value="编辑">
                </form>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
