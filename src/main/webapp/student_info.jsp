<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 40px;
        }
        .container {
            background-color: white;
            border: 2px solid #dedede;
            padding: 20px;
            border-radius: 5px;
        }
        h1 {
            color: #333;
        }
        .info {
            margin-bottom: 10px;
        }
        .label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>学生信息</h1>
    <div class="info"><span class="label">ID:</span> ${student.id}</div>
    <div class="info"><span class="label">Password:</span> ${student.password}</div>
    <div class="info"><span class="label">Gender:</span> ${student.gender}</div>
    <div class="info"><span class="label">Email:</span> ${student.email}</div>
    <div class="info"><span class="label">Address:</span> ${student.address}</div>
    <div class="info"><span class="label">Phone Number:</span> ${student.phonenumber}</div>
    <div class="info"><span class="label">Academy:</span> ${student.academy}</div>
    <div class="info"><span class="label">Mentor:</span> ${student.mentor}</div>
    <div class="info"><span class="label">Major:</span> ${student.major}</div>
    <div class="info"><span class="label">Degree:</span> ${student.degree}</div>
</div>
</body>
</html>
