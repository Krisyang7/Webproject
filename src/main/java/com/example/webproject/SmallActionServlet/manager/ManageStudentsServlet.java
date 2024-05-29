package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.Bean.Student;
import com.example.webproject.DaoImpl.StudentImpl;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ManageStudentsServlet")
public class ManageStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StudentImpl studentimp=new StudentImpl();
        String college = (String) session.getAttribute("college");

        List<Student> students = null;
        try {
            students = studentimp.search_college(college);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager_jsp/manage_students.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        StudentImpl studentimp=new StudentImpl();
        String college = (String) session.getAttribute("college");

        List<Student> students = null;
        try {
            students = studentimp.search_college(college);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manager_jsp/manage_students.jsp");
        dispatcher.forward(request, response);
    }

}
