package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Update;
import com.example.webproject.Daos.Manager_update;
import com.example.webproject.SM.SM2Utils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Manager_updateImpl implements Manager_update {

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
    public void commit_update(HttpServletRequest request) throws SQLException {
        String studentId = request.getParameter("student_id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String degree = request.getParameter("degree");
        String mentor = request.getParameter("mentor");
        String phonenumber = SM2Utils.encrypt(request.getParameter("phonenumber")) ;
        String email =SM2Utils.encrypt(request.getParameter("email")) ;
        String nativePlace = request.getParameter("nativePlace");
        String address =SM2Utils.encrypt(request.getParameter("address")) ;
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

    @Override
    public void whether_update(String action,String update_id) throws SQLException {
        Connection conn=getConnection();
        if(action.equals("approve")){
            PreparedStatement ps1=conn.prepareStatement("select * from student_updates where update_id= ?");
            ps1.setString(1,update_id);
            ResultSet rs=ps1.executeQuery();
            if(rs.next()){
                String id=rs.getString("student_id");
                String name=rs.getString("name");
                String gender=rs.getString("gender");
                String college=rs.getString("college");
                String mentor=rs.getString("mentor");
                String major=rs.getString("major");
                String degree=rs.getString("degree");
                PreparedStatement ps2=conn.prepareStatement("UPDATE students SET judgeing = 0,name= ?,gender= ?,college= ?,mentor= ?,major= ?,degree= ? WHERE student_id = ? ");
                ps2.setString(1,name);
                ps2.setString(2,gender);
                ps2.setString(3,college);
                ps2.setString(4,mentor);
                ps2.setString(5,major);
                ps2.setString(6,degree);
                ps2.setString(7,id);
                ps2.executeUpdate();
                ps2.close();
            }
            rs.close();
            ps1.close();
        }
        PreparedStatement ps=conn.prepareStatement("delete from student_updates where update_id="+update_id);
        ps.executeUpdate();
    }
}
