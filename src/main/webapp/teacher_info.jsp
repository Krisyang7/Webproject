<%@ page import="java.util.List" %>
<%@ page import="com.example.webproject.Bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>Teacher_info</title>
    <script src="js/operation.js"></script>
    <link rel="stylesheet" href="operation.css">
</head>
<body>
    <div class="top_bar">
        <h1>教师操作系统</h1>
        <a href="Login.jsp">退出登录</a>
    </div>
    <div class="container">
        <div class="side_bar">
            <div class="side_bar_button" onclick="showContent('table3')">学生信息查询</div>
            <div class="side_bar_button">学生信息更改</div>
            <div class="side_bar_button" onclick="showContent('table2')">个人信息更改</div>
            <div class="side_bar_button" onclick="showContent('table1')">个人信息查询</div>
            <h1>${teacher.id}</h1>
            <h1>${teacher.name}</h1>
        </div>

<%--        信息表--%>
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

<%--        信息修改--%>
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

<%--        学生信息查询--%>
        <div class="center_bar" id="table3">
            <form action="SearchStudents.do" method="post">
                <h1>学生信息查询</h1>
                <label for="student_id" class="first_label">学号:</label>
                <input type="text" id="student_id" placeholder="请输入学号" name="student_id" >

                <label for="name">姓名:</label>
                <input type="text" id="name" placeholder="请输入姓名" name="name" >

                <label for="mentor">辅导员名字:</label>
                <input type="text" id="mentor"placeholder="请输入辅导员名字" name="mentor" ><br><br>

                <label for="nativePlace" class="first_label">籍贯:</label>
                <input type="text" id="nativePlace" placeholder="请输入籍贯" name="nativePlace">

                <label for="college">学院:</label>
                <input type="text" id="college" placeholder="请输入学院" name="college">

                <label for="major">专业:</label>
                <input type="text" id="major" name="major"placeholder="请输入专业" ><br><br>

                <div class="button-container">
                    <input type="submit" value="提交" onclick="submitForm(event,'table3')">
                    <input type="reset" value="重置">
                </div>

            </form>

                <table border="1">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>姓名</th>
                        <th>学院</th>
                        <th>专业</th>
                        <th>辅导员</th>
                        <th>详细信息</th>
                    </tr>
                    </thead>
                    <tbody id="studentTableBody">
                    </tbody>
                </table>
<%--            详细信息显示--%>
            <div id='studentDataContainer'>
                <button class="close-btn" onclick="hideCanvas()"></button>
            </div>


            </div>

<%----%>
        </div>
</body>
</html>
