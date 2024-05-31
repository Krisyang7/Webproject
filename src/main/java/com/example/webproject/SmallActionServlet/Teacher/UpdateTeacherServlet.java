package com.example.webproject.SmallActionServlet.Teacher;

import com.example.webproject.Bean.Teacher;
import com.example.webproject.DaoImpl.TeacherImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateTeacherServlet.do")
public class UpdateTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher1 =(Teacher) req.getSession().getAttribute("teacher");
        String id=teacher1.getId();
        String phonenumber = req.getParameter("phonenumber");
        String email = req.getParameter("email");
        String address=req.getParameter("address");
        String trainstart = req.getParameter("trainstart");
        String trainend = req.getParameter("trainend");
        try {
            TeacherImpl teacher=new TeacherImpl();
            teacher.updateTeacherAddress(id,address);
            teacher.updateTeacherEmail(id,email);
            teacher.updateTeacherPhoneNumber(id,phonenumber);
            teacher.updateTeacherTrainStart(id,trainstart);
            teacher.updateTeacherTrainEnd(id,trainend);
            teacher1=teacher.SelfQuary(id);
            req.getSession().setAttribute("teacher" ,teacher1);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("teacher_info.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}