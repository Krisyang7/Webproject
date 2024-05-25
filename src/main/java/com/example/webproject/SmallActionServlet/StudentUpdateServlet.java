package com.example.webproject.SmallActionServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.webproject.Bean.Student;
import com.example.webproject.DaoImpl.StudentImpl;

import java.io.IOException;

@WebServlet("/UpdateStudentServlet")
public class StudentUpdateServlet extends HttpServlet{
    private StudentImpl studentimpl=new StudentImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String id = request.getParameter("id");
        String phoneNumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String trainStart = request.getParameter("trainstart");
        String trainEnd = request.getParameter("trainend");

        try {
            studentimpl.updateStudentEmail(id,email);
            studentimpl.updateStudentPhoneNumber(id,phoneNumber);
            studentimpl.updateStudentTraining(id,trainStart,trainEnd);

            Student updatedStudent = studentimpl.getStudentById(id);
            request.setAttribute("student", updatedStudent);
            RequestDispatcher requestDispatcher= request.getRequestDispatcher("student_info.jsp");
            requestDispatcher.forward(request,resp);


        } catch (Exception e) {
            // 处理异常情况，例如日志记录或向用户显示错误消息
            e.printStackTrace();
            // 或者向用户显示错误页面
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新学生信息时出错");
        }
    }
}

