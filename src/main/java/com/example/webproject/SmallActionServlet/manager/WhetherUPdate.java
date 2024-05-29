package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.DaoImpl.Manager_updateImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ReviewUpdateServlet")
public class WhetherUPdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String updateId=request.getParameter("updateId");
        Manager_updateImpl managerUpdate=new Manager_updateImpl();

        try {
            managerUpdate.whether_update(action,updateId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        response.sendRedirect("ReviewUpdatesServlet");
    }


}
