package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Update;
import com.example.webproject.Daos.Manager_update;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manager_updateImpl implements Manager_update {
    @Override
    public void commit_update(HttpServletRequest request) throws SQLException {
        String studentId = request.getParameter("student_id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String degree = request.getParameter("degree");
        String mentor = request.getParameter("mentor");
        String phonenumber = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String nativePlace = request.getParameter("nativePlace");
        String address = request.getParameter("address");
        String trainstart = request.getParameter("trainstart");
        String trainend = request.getParameter("trainend");
        String marryStatus = request.getParameter("marryStatus");
        String policystatus = request.getParameter("policystatus");

        Connection conn=getConnection();
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO student_updates (student_id, name, gender, college, major, degree, mentor, phonenumber, email, nativePlace, address, trainstart, trainend, marryStatus, policystatus, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending')");
        ps.setString(1, studentId);
        ps.setString(2, name);
        ps.setString(3, gender);
        ps.setString(4, college);
        ps.setString(5, major);
        ps.setString(6, degree);
        ps.setString(7, mentor);
        ps.setString(8, phonenumber);
        ps.setString(9, email);
        ps.setString(10, nativePlace);
        ps.setString(11, address);
        ps.setString(12, trainstart);
        ps.setString(13, trainend);
        ps.setString(14, marryStatus);
        ps.setString(15, policystatus);
        ps.executeUpdate();
        ps.close();

        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET judgeing = ? WHERE student_id = ?");
        stmt.setString(1,"1");
        stmt.setString(2,studentId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public List<Update> get_commit() throws SQLException {
        Connection conn=getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from student_updates");
        ResultSet rs=ps.executeQuery();
        List<Update> updates=new ArrayList<>();
        while (rs.next()) {
            Update update = new  Update(
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("nativePlace"),
                    rs.getString("phonenumber"),
                    rs.getString("college"),
                    rs.getString("trainstart"),
                    rs.getString("trainend"),
                    rs.getString("policyStatus"),
                    rs.getString("marrystatus"),
                    rs.getString("mentor"),
                    rs.getString("major"),
                    rs.getString("degree"),
                    "1",
                    rs.getString("update_id"),
                    rs.getString("submitted_by"),
                    rs.getString("submitted_at")
            );

            StudentImpl studentimp=new StudentImpl();
            Student origin=studentimp.getStudentById(rs.getString("student_id"));
            update.setOrigin(origin);

            updates.add(update);
        }
        rs.close();
        ps.close();
        conn.close();

        return  updates;
    }
}
