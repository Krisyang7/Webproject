package com.example.webproject;
import jakarta.servlet.RequestDispatcher;
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
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class InputUserServlet extends HttpServlet {
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
                    if (resultSet.next()) {
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String spellName=convertToPinyin(name);
                        String gender=resultSet.getString("gender");
                        String academy=resultSet.getString("academy");
                        String trainstart=resultSet.getString("trainstart");
                        String trainend=resultSet.getString("trainend");
                        String policyStatus=resultSet.getString("policystatus");
                        String mattyStatus=resultSet.getString("marrystatus");
                        String mentor=resultSet.getString("mentor");
                        String major=resultSet.getString("major");
                        String degree=resultSet.getString("degree");
                        String phonenumber=resultSet.getString("phonenumber");
                        String email=resultSet.getString("email");
                        String address=resultSet.getString("address");
                        String nativePlace=resultSet.getString("nativeplace");
                        Student student=new Student(id,name,password,gender,email,address,nativePlace,phonenumber,academy,trainstart,trainend,policyStatus,spellName,mattyStatus,mentor,major,degree);
                        request.getSession().setAttribute("student",student);
                        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/student_info.jsp");
                        requestDispatcher.forward(request,response);
                    }
                }
        } catch (SQLException e) {
            throw new ServletException("Error in database operation", e);
        }
    }
}
