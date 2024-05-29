package com.example.webproject.servelet;


import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.DaoImpl.LoginDaoImpl;
import com.example.webproject.DaoImpl.StudentImpl;
//import com.example.webproject.DaoImpl.TeacherImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    DataSource dataSource;
    Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/wwtweb");
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
                if (new LoginDaoImpl().isAccountLocked(userid)) {
                    System.out.println("锁定");
                    response.sendRedirect("Login.jsp?locked=true");
                    return;
                }
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String type=resultSet.getString("type");
                        String id=resultSet.getString("id");
                        request.getSession().setAttribute("id",id);

                        new LoginDaoImpl().resetFailedLoginAttempts(id);
                        //密码过期
                        if (isPasswordChangeRequired(resultSet.getDate("lastPasswordChangeDate"))) {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.jsp?overday=true");
                            dispatcher.forward(request, response);
                            return;
                        }

                        if (type.equals("0")){
                            StudentImpl studentimpl=new StudentImpl();
                            Student student=studentimpl.getStudentById(id);
                            request.getSession().setAttribute("student", student);
                            request.getSession().setMaxInactiveInterval(30 * 60);//超时30min退出
                            handleStudentLogin(id,password,request,response);
                        }
                        else if(type.equals("1")){
//                            Teacher teacher= new TeacherImpl().SelfQuary(id);
//                            request.getSession().setAttribute("teacher",teacher);
                            if(id.equals(password)){
                                RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.jsp");
                                dispatcher.forward(request, response);
                            }
                            else {
                                RequestDispatcher dispatcher=request.getRequestDispatcher("teacher_info.jsp");
                                dispatcher.forward(request,response);
                            }
                        } else if (type.equals("2")) {  //学院管理员
                            String college=resultSet.getString("college");
                            request.getSession().setAttribute("college", college);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/ManageStudentsServlet");
                            dispatcher.forward(request, response);
                        }
                    }
                    else {
                        handleFailedLogin(userid,request,response);
                    }
                }
        } catch (SQLException e) {
            throw new ServletException("Error in database operation", e);
        }
    }

    private boolean isPasswordChangeRequired(Date lastPasswordChangeDate) {
        if (lastPasswordChangeDate == null) return false; // 如果从未更改过密码，不需要更改
        long daysSinceLastChange = (new Date().getTime() - lastPasswordChangeDate.getTime()) / (1000 * 60 * 60 * 24);
        return daysSinceLastChange >= 90; // 如果距离上次更改超过90天，返回true
    }

    private void handleStudentLogin(String id, String password, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取学生信息等操作
        // 根据需要进行密码是否与id相同的判断
        if (id.equals(password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("change_password.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("student_info.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void handleTeacherLogin(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理教师登录的逻辑
    }

    // 实现登录失败尝试次数和锁定的方法
    private void handleFailedLogin(String userid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginDaoImpl loginDaoimp=new LoginDaoImpl();
        try {
            String sql = "SELECT attempt FROM login WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int attempts = resultSet.getInt("attempt");
                        attempts++;
                        if (attempts >= 5) {
                            loginDaoimp.lockAccount(userid);
                            response.sendRedirect("Login.jsp?locked=true");
                        } else {
                            loginDaoimp.updateFailedLoginAttempts(userid, attempts);
                            response.sendRedirect("Login.jsp?login=false");
                        }
                    } else {
                        loginDaoimp.createFailedLoginAttemptRecord(userid);
                        response.sendRedirect("Login.jsp?login=false");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
    }


}


