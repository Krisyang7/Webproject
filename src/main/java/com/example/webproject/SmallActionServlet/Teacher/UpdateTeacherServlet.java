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
            String diary="Update Self";
            if (!address.equals(teacher1.getAddress())){
                teacher.updateTeacherAddress(id,address);
                diary+=" address:"+address;
            }
            if (!email.equals(teacher1.getEmail())){
                teacher.updateTeacherEmail(id,email);
                diary+=" email:"+email;
            }
            if (!teacher1.getPhonenumber().equals(phonenumber)){
                teacher.updateTeacherPhoneNumber(id,phonenumber);
                diary+=" phonenumber:"+phonenumber;
            }
            if (!trainstart.equals(teacher1.getTrainstart())){
                teacher.updateTeacherTrainStart(id,trainstart);
                diary+=" trainstart:"+trainstart;
            }
            if (!trainend.equals(teacher1.getTrainend())){
                teacher.updateTeacherTrainEnd(id,trainend);
                diary+=" trainend:"+trainend;
            }

            teacher1=teacher.SelfQuary(id);
            req.getSession().setAttribute("teacher" ,teacher1);
            teacher.SetDiary(teacher1.getId(),diary);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("teacher_info.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
