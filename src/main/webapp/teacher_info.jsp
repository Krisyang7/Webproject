<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Information</title>
    <link rel="stylesheet" href="operation.css">
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
    <a href="Login.jsp">退出登录</a>
</div>
<div class="container">
    <h1>在校信息</h1>
    <form action="UpdateTeacherServlet.do" method="post">
        <input type="hidden" name="id" value="${teacher.id}" />
        <table>
            <tr>
                <td><b>老师编号:</b></td>
                <td>${teacher.id}</td>
            </tr>
            <tr>
                <td><b>姓名:</b></td>
                <td>${teacher.name}</td>
            </tr>
            <tr>
                <td><b>性别:</b></td>
                <td>${teacher.gender}</td>
            </tr>
            <tr>
                <td><b>学院:</b></td>
                <td>${teacher.college}</td>
            </tr>
            <tr>
                <td><b>名字拼音:</b></td>
                <td>${teacher.spellname}</td>
            </tr>
            <tr>
                <td><b>专业:</b></td>
                <td>${teacher.major}</td>
            </tr>
            <tr>
                <td><b>电话:</b></td>
                <td><input type="text" name="phonenumber" value="${teacher.phonenumber}" /></td>
            </tr>
            <tr>
                <td><b>Email:</b></td>
                <td><input type="email" name="email" value="${teacher.email}" /></td>
            </tr>
            <tr>
                <td><b>家庭地址:</b></td>
                <td><input type="text" name="address" value="${teacher.address}" /></td>
            </tr>
            <tr>
                <td><b>籍贯:</b></td>
                <td>${teacher.nativePlace}</td>
            </tr>
            <tr>
                <td><b>火车起始站:</b></td>
                <td><input type="text" name="trainstart" value="${teacher.trainstart}" /></td>
            </tr>
            <tr>
                <td><b>火车终点站:</b></td>
                <td><input type="text" name="trainend" value="${teacher.trainend}" /></td>
            </tr>
            <tr>
                <td><b>婚姻情况:</b></td>
                <td>${teacher.marrystatus}</td>
            </tr>
            <tr>
                <td><b>职务:</b></td>
                <td>${teacher.status}</td>
            </tr>
        </table>
        <div>
            <input type="submit" value="更新信息" />
        </div>
    </form>
</div>
</body>
</html>
