package com.example.webproject.servelet;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

import com.example.webproject.Bean.Student;
import com.example.webproject.Daos.StudentImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
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
                            StudentImpl studentimpl=new StudentImpl();
                            Student student=studentimpl.getStudentById(id);
                            request.setAttribute("student", student);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("student_info.jsp");
                            dispatcher.forward(request, response);
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
