package com.example.webproject.SmallActionServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UpdateTeacherServlet.do")
public class UpdateTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phonenumber = req.getParameter("phonenumber");
        String email = req.getParameter("email");
        String address=req.getParameter("address");
        String trainstart = req.getParameter("trainstart");
        String trainend = req.getParameter("trainend");

    }
}
