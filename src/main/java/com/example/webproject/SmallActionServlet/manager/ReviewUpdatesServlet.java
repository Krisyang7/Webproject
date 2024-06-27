package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Update;
import com.example.webproject.DaoImpl.Manager_updateImpl;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/ReviewUpdatesServlet")
public class ReviewUpdatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Update> updates = new ArrayList<>();
        Manager_updateImpl managerUpdateimp=new Manager_updateImpl();
        try {
            updates=managerUpdateimp.get_commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("updates", updates);
        request.getRequestDispatcher("manager_jsp/review_updates.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Update> updates = new ArrayList<>();
        Manager_updateImpl managerUpdateimp=new Manager_updateImpl();
        try {
            updates=managerUpdateimp.get_commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("updates", updates);
        request.getRequestDispatcher("manager_jsp/review_updates.jsp").forward(request, response);
    }
}
