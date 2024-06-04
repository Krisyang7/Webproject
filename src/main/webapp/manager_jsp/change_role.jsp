<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage User</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 50%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-container {
            margin-bottom: 20px;
        }
        .form-container label {
            display: block;
            margin-bottom: 5px;
        }
        .form-container input, .form-container select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #007BFF;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .user-info {
            margin-top: 20px;
        }
        .message {
            text-align: center;
            margin-top: 20px;
            padding: 10px;
            border-radius: 4px;
        }
        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>管理用户角色和权限</h1>

    <div class="form-container">
        <form action="AdminManageUserServlet" method="post">
            <label for="userId">用户ID:</label>
            <input type="text" id="userId" name="userId" required>
            <button type="submit" class="btn">查询</button>
        </form>
    </div>

    <%
        String userId = (String) request.getAttribute("userId");
        Integer userType = (Integer) request.getAttribute("userType");
        String message = (String) request.getAttribute("message");
        String messageType = (String) request.getAttribute("messageType");
        if (message != null && messageType != null) {
    %>
    <div class="message <%= messageType %>">
        <%= message %>
    </div>
    <%
        }
        if (userId != null && userType != null) {
    %>
    <div class="user-info">
        <form action="AdminManageUserServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="userId" value="<%= userId %>">

            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" value="<%= userId %>" readonly>

            <label for="role">角色:</label>
            <select id="role" name="role" required>
                <option value="0" <%= userType == 0 ? "selected" : "" %>>学生</option>
                <option value="1" <%= userType == 1 ? "selected" : "" %>>研究生院领导、学校领导</option>
                <option value="2" <%= userType == 2 ? "selected" : "" %>>学院研究生秘书、学院领导</option>
                <option value="3" <%= userType == 3 ? "selected" : "" %>>审核员</option>
                <option value="4" <%= userType == 4 ? "selected" : "" %>>研究生院领导、学校领导</option>
                <option value="5" <%= userType == 5 ? "selected" : "" %>>审计管理员</option>
                <option value="6" <%= userType == 6 ? "selected" : "" %>>系统管理员</option>
            </select>

            <button type="submit" class="btn">保存</button>
        </form>
    </div>
    <%
        }
    %>
</div>
</body>
</html>
