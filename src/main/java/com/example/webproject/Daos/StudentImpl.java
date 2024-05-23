package com.example.webproject.Daos;

import com.example.webproject.Bean.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class StudentImpl implements StudentDao{
    @Override
    public Student getStudentById(String studentId) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Assuming a constructor that matches the fetched columns
            return new Student(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getString("nativePlace"),
                    rs.getString("college"),
                    rs.getString("phonenumber"),
                    rs.getString("academy"),
                    rs.getString("trainstart"),
                    rs.getString("trainend"),
                    rs.getString("policyStatus"),
                    rs.getString("nameSpell"),
                    rs.getString("marrystatus"),
                    rs.getString("mentor"),
                    rs.getString("major"),
                    rs.getString("degree")
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return null;
    }

    @Override
    public void updateStudentPhoneNumber(String studentId, String newPhoneNumber) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET phonenumber = ? WHERE id = ?");
        stmt.setString(1, newPhoneNumber);
        stmt.setString(2, studentId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public void updateStudentEmail(String studentId, String newEmail) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET email = ? WHERE id = ?");
        stmt.setString(1, newEmail);
        stmt.setString(2, studentId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void updateStudentTraining(String studentId, String trainStart, String trainEnd) throws SQLException {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE students SET trainstart = ?, trainend = ? WHERE id = ?");
            stmt.setString(1, trainStart);
            stmt.setString(2, trainEnd);
            stmt.setString(3, studentId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
    }

}
