package com.example.webproject;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class InputUserServlet extends HttpServlet {
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
            String sql = "select * from students where id=? and password=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String gender=resultSet.getString("gender");
                        String academy=resultSet.getString("academy");
                        String mentor=resultSet.getString("mentor");
                        String major=resultSet.getString("major");
                        String degree=resultSet.getString("degree");
                        String phonenumber=resultSet.getString("phonenumber");
                        String email=resultSet.getString("email");
                        String address=resultSet.getString("address");
                        Student student=new Student(id,password,gender,email,address,phonenumber,academy,mentor,major,degree);
                        System.out.println(student);
                    }
                }
        } catch (SQLException e) {
            throw new ServletException("Error in database operation", e);
        }
    }
}
