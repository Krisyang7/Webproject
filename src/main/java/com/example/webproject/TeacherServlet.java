package com.example.webproject;

import com.example.webproject.Bean.Teacher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherServlet extends HttpServlet {
    Connection connection;
    String id;

    //    拼音转换
    public static String convertToPinyin(String name) {
        StringBuilder pinyinBuilder = new StringBuilder();
        for (char c : name.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                pinyinBuilder.append(pinyinArray[0]);
            } else {
                pinyinBuilder.append(c);
            }
        }
        return pinyinBuilder.toString();
    }

    public TeacherServlet(String id, Connection connection) {
        this.connection = connection;
        this.id = id;
    }

    ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String sql = "select * from teacher where teacher_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String id = resultSet.getString("teacher_id");
                    String name = resultSet.getString("name");
                    String spellName = convertToPinyin(name);
                    String gender = resultSet.getString("gender");
                    String email = resultSet.getString("email");
                    String college = resultSet.getString("college");
                    String major = resultSet.getString("major");
                    String address = resultSet.getString("address");
                    String nativePlace = resultSet.getString("nativeplace");
                    String phonenumber = resultSet.getString("phonenumber");
                    String academy = resultSet.getString("academy");
                    String trainstart = resultSet.getString("trainstart");
                    String trainend = resultSet.getString("trainend");
                    String policyStatus = resultSet.getString("policystatus");
                    String mattyStatus = resultSet.getString("marrystatus");
                    String status = resultSet.getString("status");
                    Teacher teacher = new Teacher(id, name, gender, email, address, nativePlace, college, phonenumber, academy, trainstart, trainend, policyStatus, spellName, mattyStatus, major, status);
                    req.getSession().setAttribute("teacehr", teacher);
                    RequestDispatcher requestDispatcher = req.getRequestDispatcher("/teacher_info.jsp");
                    requestDispatcher.forward(req,resp);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error in database operation", e);
        }
    }
}
