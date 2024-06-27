package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.DaoImpl.Manager_updateImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;


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
            if(action.equals("approve"))
                managerUpdate.SetDiary((String) request.getSession().getAttribute("id"),"批准审核请求");
            else
                managerUpdate.SetDiary((String) request.getSession().getAttribute("id"),"驳回审核请求");
            managerUpdate.whether_update(action,updateId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        response.sendRedirect("ReviewUpdatesServlet");
    }


}
