<%@ page import="com.example.webproject.Bean.Student" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-input {
            margin: 10px 0;
        }
        .form-input input, .form-input select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            box-sizing: border-box;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>编辑学生信息</h1>
    <%
        Student student = (Student) request.getAttribute("student");
        if (student == null) {
            response.sendRedirect("manage_students.jsp?error=StudentNotFound");
            return;
        }
    %>
    <form id="editForm" action="UpdateStudent_judgeServlet" method="post">
        <input type="hidden" name="student_id" value="<%= student.getId() %>" />
        <div class="form-input">
            <label>姓名:</label>
            <input type="text" name="name" value="<%= student.getName() %>" required />
        </div>
        <div class="form-input">
            <label>性别:</label>
            <input type="text" name="gender" value="<%= student.getGender() %>" required />
        </div>
        <div class="form-input">
            <label>身份证号:</label>
            <input type="text" name="student_id" value="<%= student.getId() %>" required />
        </div>
        <div class="form-input">
            <label>学院:</label>
            <input type="text" name="college" value="<%= student.getCollege() %>" required />
        </div>
        <div class="form-input">
            <label>专业:</label>
            <input type="text" name="major" value="<%= student.getMajor() %>" required />
        </div>
        <div class="form-input">
            <label>学位类型:</label>
            <input type="text" name="degree" value="<%= student.getDegree() %>" required />
        </div>
        <div class="form-input">
            <label>导师:</label>
            <input type="text" name="mentor" value="<%= student.getMentor() %>" required />
        </div>
        <div class="form-input buttons">
            <input type="submit" value="提交修改" />
            <button type="button" onclick="returnToManage()">返回</button>
        </div>
    </form>
    <script>
        function returnToManage() {
            // Change form action to ReturnToManageServlet and submit
            document.getElementById('editForm').action = 'ManageStudentsServlet';
            document.getElementById('editForm').method = 'post';
            document.getElementById('editForm').submit();
        }
    </script>
</div>
</body>
</html>
