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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
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
    %>
    <table>
        <thead>
        <tr>
            <th>学生编号</th>
            <th>字段</th>
            <th>当前值</th>
            <th>更新值</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Update update : updates) {
                Student current = update.getOrigin();
        %>
        <tr>
            <td rowspan="7"><%= update.getId() %></td>
            <td>姓名</td>
            <td><%= current.getName() %></td>
            <td class="<%= !update.getName().equals(current.getName()) ? "highlight" : "" %>"><%= update.getName() %></td>
            <td rowspan="7">
                <form action="ApproveUpdateServlet" method="post" style="display:inline;">
                    <input type="hidden" name="update_id" value="<%= update.getUpdate_id() %>">
                    <button type="submit" name="action" value="批准" class="btn">批准</button>
                </form>
                <form action="RejectUpdateServlet" method="post" style="display:inline;">
                    <input type="hidden" name="update_id" value="<%= update.getUpdate_id() %>">
                    <button type="submit" name="action" value="拒绝" class="btn btn-reject">拒绝</button>
                </form>
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td><%= current.getGender() %></td>
            <td class="<%= !update.getGender().equals(current.getGender()) ? "highlight" : "" %>"><%= update.getGender() %></td>
        </tr>
        <tr>
            <td>学院</td>
            <td><%= current.getCollege() %></td>
            <td class="<%= !update.getCollege().equals(current.getCollege()) ? "highlight" : "" %>"><%= update.getCollege() %></td>
        </tr>
        <tr>
            <td>专业</td>
            <td><%= current.getMajor() %></td>
            <td class="<%= !update.getMajor().equals(current.getMajor()) ? "highlight" : "" %>"><%= update.getMajor() %></td>
        </tr>
        <tr>
            <td>学位类型</td>
            <td><%= current.getDegree() %></td>
            <td class="<%= !update.getDegree().equals(current.getDegree()) ? "highlight" : "" %>"><%= update.getDegree() %></td>
        </tr>
        <tr>
            <td>导师</td>
            <td><%= current.getMentor() %></td>
            <td class="<%= !update.getMentor().equals(current.getMentor()) ? "highlight" : "" %>"><%= update.getMentor() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>
