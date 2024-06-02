package com.example.webproject.SmallActionServlet.Teacher;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webproject.Bean.JsonUtils;
import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.DaoImpl.TeacherImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SearchTeacher.do")
public class TeacherQuaryTeacher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        TeacherImpl teacher=new TeacherImpl();
        InputStreamReader reader = new InputStreamReader((ServletInputStream) req.getInputStream());

        JSONObject object = JsonUtils.getJsonArrayFromObject(reader);
            String id=object.getString("teacher_id");
            String name=object.getString("Tname");
            String college=object.getString("Tcollege");
            String stasus=object.getString("status");
            Teacher teacher1=(Teacher) req.getSession().getAttribute("teacher");
            try {
                List<Teacher> teacherList = teacher.QuaryTeacher(teacher1.getId(),id, name,college, stasus);
                ObjectMapper mapper = new ObjectMapper(); // ObjectMapper是Jackson库中的类
                String jsonString = null;
                try {
                    jsonString = mapper.writeValueAsString(teacherList);
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

