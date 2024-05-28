<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        /* 样式修改为您提供的样式 */

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form {
            width: 300px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333; /* 修改字体颜色 */
        }

        .form-control {
            width: 100%;
            padding: 8px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .btn {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <form action="ChangePasswordServlet" method="post" class="form" onsubmit="return validatePassword()">
        <input type="hidden" name="id" value="${sessionScope.id}" />
        <div class="form-group">
            <label for="oldPassword">Old Password:</label>
            <input type="password" id="oldPassword" name="oldPassword" class="form-control" required />
            <% if (session.getAttribute("error") != null) { %>
            <div class="error"><%= session.getAttribute("error") %></div>
            <% session.removeAttribute("error");
            } %>
        </div>
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" class="form-control" required />
            <div id="passwordError" class="error"></div>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm New Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required />
            <div id="confirmPasswordError" class="error"></div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn">Change Password</button>
        </div>
        <%
            String overday = request.getParameter("overday");
            if (overday != null && overday.equals("true")) {
        %>
        <div class="error-message">
            密码过期！
        </div>
        <%
            }
        %>
    </form>
</div>

<script>
    function validatePassword() {
        var newPassword = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        var passwordError = document.getElementById("passwordError");
        var confirmPasswordError = document.getElementById("confirmPasswordError");
        var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$/;

        if (!passwordPattern.test(newPassword)) {
            passwordError.innerText = "密码必须包含数字、大小写字母和特殊字符，且长度至少为8位。";
            return false;
        } else {
            passwordError.innerText = "";
        }

        if (newPassword !== confirmPassword) {
            confirmPasswordError.innerText = "两次输入的密码不一致。";
            return false;
        } else {
            confirmPasswordError.innerText = "";
        }

        return true;
    }
</script>

</body>
</html>
