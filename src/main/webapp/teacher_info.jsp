
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

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
            <div class="side_bar_button">学生信息搜索</div>
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
                    <td>${teacher.marrystatus}</td>
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
                    <td>${teacher.marrystatus}</td>
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

    </div>

</body>
</html>
