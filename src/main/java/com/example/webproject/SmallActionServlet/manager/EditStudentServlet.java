package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.Bean.Student;
import com.example.webproject.DaoImpl.StudentImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("student_id");
        StudentImpl studentimp=new StudentImpl();
        Student student = null;
        try {
            student = studentimp.getStudentById(studentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (student != null) {
            request.setAttribute("student", student);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manager_jsp/edit_student.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("manage_students.jsp?error=StudentNotFound");
        }
    }
}
