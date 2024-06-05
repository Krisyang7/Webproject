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
            margin-top: 20px;
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
        .tab-buttons {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .tab-buttons button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            background-color: #007BFF;
            color: white;
        }
        .tab-buttons button:hover {
            background-color: #0056b3;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
        }
        .message {
            text-align: center;
            margin-top: 20px;
            padding: 10px;
            border-radius: 4px;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
    <script>
        function showTab(tabId) {
            var tabs = document.getElementsByClassName('tab-content');
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove('active');
            }
            document.getElementById(tabId).classList.add('active');
        }
    </script>
</head>
<body>
<div class="top_bar">
    <a href="Login.jsp">退出登录</a>
</div>
<div class="container">
    <h1>学生信息管理</h1>

    <% String message = (String) request.getAttribute("message"); %>
    <% String messageType = (String) request.getAttribute("messageType"); %>
    <% if (message != null && messageType != null) { %>
    <div class="message <%= messageType %>">
        <%= message %>
    </div>
    <% } %>

    <div class="tab-buttons">
        <button onclick="showTab('allStudentsTab')">所有学生</button>
        <button onclick="showTab('searchStudentTab')">根据学号查询学生</button>
    </div>

    <div id="allStudentsTab" class="tab-content active">
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
                <th>审核状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Student> students = (List<Student>) request.getAttribute("students");
                if (students != null) {
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
                <td><%= student.getJudgeing().equals("1") ? "审核中" : "已审核" %></td>
                <td>
                    <form action="EditStudentServlet" method="get" style="display:inline;">
                        <input type="hidden" name="student_id" value="<%= student.getId() %>">
                        <input type="submit" value="编辑">
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>

    <div id="searchStudentTab" class="tab-content">
        <h2>根据学号查询学生</h2>
        <form action="SearchStudentServlet" method="post">
            <label for="studentId">学号:</label>
            <input type="text" id="studentId" name="studentId" required>
            <button type="submit" class="btn">查询</button>
        </form>
    </div>
</div>
</body>
</html>
