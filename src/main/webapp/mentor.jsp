<%--
  Created by IntelliJ IDEA.
  User: 28365
  Date: 2024/5/31
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mentor</title>
    <script src="js/operation.js"></script>
    <link rel="stylesheet" href="css/operation.css">

</head>
<style>
    #YjsSearch {
        display: block;
        margin: 0 auto;
        padding: 10px 20px;
        background-color: #2196F3;
        color: white;
        border: none;
        text-align: center;
        text-decoration: none;
        font-size: 16px;
        cursor: pointer;
        border-radius: 8px;
    }
</style>
<body>
<div class="top_bar">
    <h1>导师操作系统</h1>
    <a href="Login.jsp">退出登录</a>
</div>
<div class="container">
    <div class="side_bar">
        <div class="side_bar_button" onclick="showContent('table5')">研究生信息查询</div>
        <div class="side_bar_button" onclick="showContent('table2')">个人信息更改</div>
        <div class="side_bar_button" onclick="showContent('table1')">个人信息查询</div>
        <h1>${teacher.id}</h1>
        <h1>${teacher.name}</h1>
    </div>
    <div class="center_bar" id="table1">
        <h1>老师信息表</h1>
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
                <td>${teacher.phonenumber}</td>
            </tr>
            <tr>
                <td><b>Email:</b></td>
                <td>${teacher.email}</td>
            </tr>
            <tr>
                <td><b>家庭地址:</b></td>
                <td>${teacher.address}</td>
            </tr>
            <tr>
                <td><b>籍贯:</b></td>
                <td>${teacher.nativePlace}</td>
            </tr>
            <tr>
                <td><b>火车起始站:</b></td>
                <td>${teacher.trainstart}</td>
            </tr>
            <tr>
                <td><b>火车终点站:</b></td>
                <td>${teacher.trainend}</td>
            </tr>
            <tr>
                <td><b>婚姻情况:</b></td>
                <td>${teacher.marryStatus}</td>
            </tr>
            <tr>
                <td><b>职务:</b></td>
                <td>${teacher.status}</td>
            </tr>
        </table>


    </div>
    <div class="center_bar" id="table2">
        <form action="UpdateTeacherServlet.do" method="post">
            <h1>老师信息修改表</h1>
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
                    <td>${teacher.marryStatus}</td>
                </tr>
                <tr>
                    <td><b>职务:</b></td>
                    <td>${teacher.status}</td>
                </tr>
            </table>
            <button type="submit" class="submit-btn">提交修改</button>
            <button type="reset" class="reset-btn">重置</button>
        </form>
    </div>

    <div class="center_bar" id="table5">
        <h1>研究生信息查询</h1>

        <button id="YjsSearch" onclick="mentorQuary()">查询</button>
        <table border="1">
            <thead>
            <tr>
                <th>id</th>
                <th>姓名</th>
                <th>学院</th>
                <th>专业</th>
                <th>详细信息</th>
            </tr>
            </thead>
            <tbody id="MentorBody">
            </tbody>
        </table>
            <div id="MentorDetail">
            </div>

    </div>
</div>
</body>
</html>
