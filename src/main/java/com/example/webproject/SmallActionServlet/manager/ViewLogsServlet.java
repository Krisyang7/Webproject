package com.example.webproject.SmallActionServlet.manager;

import com.example.webproject.Bean.Diary;
import com.example.webproject.DaoImpl.DiaryDaoImpl;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/ViewLogsServlet")
public class ViewLogsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected DiaryDaoImpl diaryDao = new DiaryDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Diary> diaries = diaryDao.getAllDiaries();
        request.setAttribute("diaries", diaries);
        request.getRequestDispatcher("/view_logs.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Diary> diaries = diaryDao.getAllDiaries();
        request.setAttribute("diaries", diaries);
        request.getRequestDispatcher("/view_logs.jsp").forward(request, response);
    }
}
