<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webproject.Bean.Diary" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>View Logs</title>
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
  </style>
</head>
<body>
<div class="container">
  <h1>查看所有日志</h1>
  <%
    List<Diary> diaries = (List<Diary>) request.getAttribute("diaries");
    if (diaries == null || diaries.isEmpty()) {
  %>
  <p>没有日志信息。</p>
  <%
  } else {
  %>
  <table>
    <thead>
    <tr>
      <th>操作员</th>
      <th>操作</th>
      <th>操作时间</th>
    </tr>
    </thead>
    <tbody>
    <%
      for (Diary diary : diaries) {
    %>
    <tr>
      <td><%= diary.getOperator() %></td>
      <td><%= diary.getAction() %></td>
      <td><%= diary.getActionTime() %></td>
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
