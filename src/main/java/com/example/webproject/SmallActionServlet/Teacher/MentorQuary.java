package com.example.webproject.SmallActionServlet.Teacher;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.DaoImpl.TeacherImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/mentorQuary.do")
public class MentorQuary extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        if (reader != null) {
            json = reader.readLine();
            TeacherImpl teacher=new TeacherImpl();
            Teacher teacher1 = (Teacher)req.getSession().getAttribute("teacher");
            List<Student> list = teacher.MentorQuary(teacher1.getId());
            ObjectMapper mapper = new ObjectMapper(); // ObjectMapper是Jackson库中的类
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(list);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                try (PrintWriter out = resp.getWriter()) {
                    out.print(jsonString);
                } catch (IOException e) {
                    e.printStackTrace(); // 或者适当的错误处理
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
