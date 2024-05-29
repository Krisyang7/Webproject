<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webproject.Bean.Update" %>
<%@ page import="com.example.webproject.Bean.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Student Updates</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .update-card {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            background-color: #fff;
        }
        .update-card h2 {
            margin-top: 0;
        }
        .update-details {
            display: flex;
            flex-wrap: wrap;
        }
        .update-details div {
            flex: 1;
            padding: 10px;
            box-sizing: border-box;
        }
        .update-details div:nth-child(odd) {
            background-color: #f9f9f9;
        }
        .highlight {
            background-color: #ff0;
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
        .btn-reject {
            background-color: #dc3545;
        }
        .btn-reject:hover {
            background-color: #c82333;
        }
        .operation-buttons {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>审核学生信息更新</h1>
    <%
        List<Update> updates = (List<Update>) request.getAttribute("updates");
        if (updates == null || updates.isEmpty()) {
    %>
    <p>没有待审核的更新记录。</p>
    <%
    } else {
        for (Update update : updates) {
            Student current = update.getOrigin();
    %>
    <div class="update-card">
        <h2>更新记录 ID: <%= update.getUpdate_id() %></h2>
        <div class="update-details">
            <div>
                <strong>学生编号:</strong> <%= update.getId() %>
            </div>
            <div>
                <strong>姓名:</strong>
                <span class="<%= !update.getName().equals(current.getName()) ? "highlight" : "" %>">
                    <%= update.getName() %>
                </span>
                (当前值: <%= current.getName() %>)
            </div>
            <div>
                <strong>性别:</strong>
                <span class="<%= !update.getGender().equals(current.getGender()) ? "highlight" : "" %>">
                    <%= update.getGender() %>
                </span>
                (当前值: <%= current.getGender() %>)
            </div>
            <div>
                <strong>学院:</strong>
                <span class="<%= !update.getCollege().equals(current.getCollege()) ? "highlight" : "" %>">
                    <%= update.getCollege() %>
                </span>
                (当前值: <%= current.getCollege() %>)
            </div>
            <div>
                <strong>专业:</strong>
                <span class="<%= !update.getMajor().equals(current.getMajor()) ? "highlight" : "" %>">
                    <%= update.getMajor() %>
                </span>
                (当前值: <%= current.getMajor() %>)
            </div>
            <div>
                <strong>学位类型:</strong>
                <span class="<%= !update.getDegree().equals(current.getDegree()) ? "highlight" : "" %>">
                    <%= update.getDegree() %>
                </span>
                (当前值: <%= current.getDegree() %>)
            </div>
            <div>
                <strong>导师:</strong>
                <span class="<%= !update.getMentor().equals(current.getMentor()) ? "highlight" : "" %>">
                    <%= update.getMentor() %>
                </span>
                (当前值: <%= current.getMentor() %>)
            </div>
        </div>
        <div class="operation-buttons">
            <form action="ReviewUpdateServlet" method="post" style="display: inline;">
                <input type="hidden" name="updateId" value="<%= update.getUpdate_id() %>">
                <button type="submit" name="action" value="approve" class="btn">批准</button>
            </form>
            <form action="ReviewUpdateServlet" method="post" style="display: inline;">
                <input type="hidden" name="updateId" value="<%= update.getUpdate_id() %>">
                <button type="submit" name="action" value="reject" class="btn btn-reject">拒绝</button>
            </form>
        </div>
    </div>
    <%
            }
        }
    %>
</div>
</body>
</html>
