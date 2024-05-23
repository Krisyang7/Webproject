package com.example.webproject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sourceforge.pinyin4j.PinyinHelper;

//import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    DataSource dataSource;
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ybcweb");
        } catch (Exception e) {
            throw new ServletException("Error initializing data source", e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            String userid = request.getParameter("id");
            String password = request.getParameter("password");
            String sql = "select * from login where login.id=? and login.password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String type=resultSet.getString("type");
                        String id=resultSet.getString("id");
//                        搜索结果为学生
                        if (type.equals("0")){

                        }
                        else {
                            TeacherServlet teacherServlet=new TeacherServlet(id,connection);
                        }
                    }
                }
        } catch (SQLException e) {
            throw new ServletException("Error in database operation", e);
        }
    }
}
