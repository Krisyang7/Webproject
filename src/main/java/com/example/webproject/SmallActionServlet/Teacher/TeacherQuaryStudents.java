package com.example.webproject.SmallActionServlet.Teacher;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webproject.Bean.JsonUtils;
import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.DaoImpl.TeacherImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SearchStudents.do")
public class TeacherQuaryStudents extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        InputStreamReader reader = new InputStreamReader((ServletInputStream) req.getInputStream());

        JSONArray list = JsonUtils.getJsonArrayFromObject(reader);

        for (int i = 0; i < list.size(); i++) {
            JSONObject object = (JSONObject) list.get(i);
           String id=object.getString("student_id");
           String name=object.getString("name");
           String mentor=object.getString("mentor");
           String nativePlace=object.getString("nativePlace");
           String college=object.getString("college");
           String major=object.getString("major");
            TeacherImpl teacher=new TeacherImpl();
            try {
                List<Student> studentList = teacher.QuaryStudent(id, name, mentor, nativePlace, college, major);
                ObjectMapper mapper = new ObjectMapper(); // ObjectMapper是Jackson库中的类
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(studentList);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");

// 获取输出流并写入JSON字符串
                try (PrintWriter out = resp.getWriter()) {
                    out.print(jsonString);
                } catch (IOException e) {
                    e.printStackTrace(); // 或者适当的错误处理
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
