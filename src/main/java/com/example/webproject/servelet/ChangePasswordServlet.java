package com.example.webproject.servelet;

import com.example.webproject.Daos.LoginDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
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
            request.setAttribute("error", "原密码输入错误");
            request.getRequestDispatcher("change_password.jsp").forward(request, response);
            return;
        }

        // 密码修改成功，执行更新操作
        try {
            loginDao.UpdatePassword(id, newPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("Login.jsp");


    }
}
