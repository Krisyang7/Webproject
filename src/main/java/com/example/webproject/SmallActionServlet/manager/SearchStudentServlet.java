package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.Bean.Student;
import com.example.webproject.DaoImpl.StudentImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected StudentImpl studentDao = new StudentImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String college= (String) request.getSession().getAttribute("college");
        Student student = null;
        try {
            student = studentDao.getStudentByIdandcollege(studentId,college);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (student == null) {
            request.setAttribute("message", "无此学生");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("ManageStudentsServlet").forward(request, response);
        } else {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/manager_jsp/edit_student.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String college= (String) request.getSession().getAttribute("college");
        Student student = null;
        try {
            student = studentDao.getStudentByIdandcollege(studentId,college);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (student == null) {
            request.setAttribute("message", "无此学生");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("ManageStudentsServlet").forward(request, response);
        } else {
            request.setAttribute("student", student);
            request.getRequestDispatcher("/manager_jsp/edit_student.jsp").forward(request, response);
        }
    }
}
