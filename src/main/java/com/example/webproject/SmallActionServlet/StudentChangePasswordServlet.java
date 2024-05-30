package com.example.webproject.SmallActionServlet;

import com.example.webproject.DaoImpl.LoginDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ChangePasswordServlet")
public class StudentChangePasswordServlet extends HttpServlet {
    private LoginDaoImpl loginDao=new LoginDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        // 根据 ID 查询原密码
        String savedPassword = null;
        try {
            savedPassword = loginDao.GetPassword(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 检查输入的旧密码是否正确
        if (!oldPassword.equals(savedPassword)) {
            request.getSession().setAttribute("error", "原密码输入错误");
            response.sendRedirect("change_password.jsp");
            return;
        }

        // 密码修改成功，执行更新操作
        try {
            loginDao.UpdatePassword(id, newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("Login.jsp?passwordChanged=true");


    }
}
