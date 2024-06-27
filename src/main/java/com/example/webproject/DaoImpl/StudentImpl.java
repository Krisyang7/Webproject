package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Daos.StudentDao;
import com.example.webproject.SM.SM2Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl implements StudentDao {

    public void SetDiary(String name,String sql){
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            String s="insert into diary values (?,?,?)";
            Connection connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setString(3,formattedDateTime);
            preparedStatement.setString(2,sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public Student getStudentById(String studentId) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE student_id = ?");
        stmt.setString(1, studentId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Assuming a constructor that matches the fetched columns
            return new Student(
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    SM2Utils.decrypt(rs.getString("email")) ,
                    SM2Utils.decrypt(rs.getString("address")) ,
                    rs.getString("nativePlace"),
                    SM2Utils.decrypt(rs.getString("phonenumber")) ,
                    rs.getString("college"),
                    rs.getString("trainstart"),
                    rs.getString("trainend"),
                    rs.getString("policyStatus"),
                    rs.getString("marrystatus"),
                    rs.getString("mentor"),
                    rs.getString("major"),
                    rs.getString("degree"),
                    rs.getString("judgeing")
            );
        }
        rs.close();
        stmt.close();
        conn.close();
        return null;
    }

    @Override
    public Student getStudentByIdandcollege(String studentId, String college) throws SQLException{
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE student_id = ? and college= ?");
        stmt.setString(1, studentId);
        stmt.setString(2, college);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Assuming a constructor that matches the fetched columns
            return new Student(
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    SM2Utils.decrypt(rs.getString("email")) ,
                    SM2Utils.decrypt(rs.getString("address")) ,
                    rs.getString("nativePlace"),
                    SM2Utils.decrypt(rs.getString("phonenumber")) ,
                    rs.getString("college"),
                    rs.getString("trainstart"),
                    rs.getString("trainend"),
                    rs.getString("policyStatus"),
                    rs.getString("marrystatus"),
                    rs.getString("mentor"),
                    rs.getString("major"),
                    rs.getString("degree"),
                    rs.getString("judgeing")
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
        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET phonenumber = ? WHERE student_id = ?");
        stmt.setString(1,SM2Utils.encrypt(newPhoneNumber) );
        stmt.setString(2, studentId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public void updateStudentEmail(String studentId, String newEmail) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?");
        stmt.setString(1,SM2Utils.encrypt(newEmail) );
        stmt.setString(2, studentId);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void updateStudentTraining(String studentId, String trainStart, String trainEnd) throws SQLException {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE students SET trainstart = ?, trainend = ? WHERE student_id = ?");
            stmt.setString(1, trainStart);
            stmt.setString(2, trainEnd);
            stmt.setString(3, studentId);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
    }

    public List<Student> search_college(String college) throws SQLException {
        List<Student> students=new ArrayList<>();
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE college = ?");
        stmt.setString(1,college);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Student student = new  Student(
                    rs.getString("student_id"),
                    rs.getString("name"),
                    rs.getString("gender"),
                    SM2Utils.decrypt(rs.getString("email")) ,
                    SM2Utils.decrypt(rs.getString("address")) ,
                    SM2Utils.decrypt(rs.getString("nativePlace")),
                    SM2Utils.decrypt(rs.getString("phonenumber")) ,
                    rs.getString("college"),
                    rs.getString("trainstart"),
                    rs.getString("trainend"),
                    rs.getString("policyStatus"),
                    rs.getString("marrystatus"),
                    SM2Utils.decrypt(rs.getString("mentor")),
                    SM2Utils.decrypt(rs.getString("major")),
                    rs.getString("degree"),
                    rs.getString("judgeing")
            );
            students.add(student);
        }
        rs.close();
        stmt.close();
        conn.close();
        return students;
    }


}
