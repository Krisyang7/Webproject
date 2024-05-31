package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.Daos.TeacherDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherImpl implements TeacherDao {

    @Override
    public List<Student> QuaryStudent(String id,String name,String mentor,String nativePlace,String college,String major ) throws SQLException {
        String sql = "SELECT * FROM students " +
                "WHERE " +
                "    (student_id = ? OR ? = '') " + // 如果student_id参数为空字符串，则始终为真
                "    AND (name LIKE CONCAT('%', ?, '%') OR ? = '') " + // 如果name参数为空字符串，则不应用过滤条件
                "    AND (mentor LIKE CONCAT('%', ?, '%') OR ? = '') " +
                "    AND (nativePlace = ? OR ? = '') " +
                "    AND (college LIKE CONCAT('%', ?, '%') OR ? = '') " +
                "    AND (major LIKE CONCAT('%', ?, '%') OR ? = '')";
        List<Student> list=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,id);

        preparedStatement.setString(3,name);
        preparedStatement.setString(4,name);

        preparedStatement.setString(5,mentor);
        preparedStatement.setString(6,mentor);

        preparedStatement.setString(7,nativePlace);
        preparedStatement.setString(8,nativePlace);

        preparedStatement.setString(9,college);
        preparedStatement.setString(10,college);

        preparedStatement.setString(11,major);
        preparedStatement.setString(12,major);

        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Student(set.getString("student_id"),set.getString("name"),set.getString("gender"),set.getString("email"),set.getString("address"),set.getString("nativePlace"),set.getString("phonenumber"),set.getString("college"),set.getString("trainstart"),set.getString("trainend"),set.getString("policyStatus"),set.getString("marrystatus"),set.getString("mentor"),set.getString("major"),set.getString("degree")));
        }
        return list;
    }

    public List<Teacher> QuaryTeacher(String id,String name,String college,String status ) throws SQLException {
        String sql = "SELECT * FROM teacher " +
                "WHERE " +
                "    (teacher_id = ? OR ? = '') " + // 如果student_id参数为空字符串，则始终为真
                "    AND (name LIKE CONCAT('%', ?, '%') OR ? = '') " + // 如果name参数为空字符串，则不应用过滤条件
                "    AND (college LIKE CONCAT('%', ?, '%') OR ? = '') " +
                "    AND (status=? or ?='')";
        List<Teacher> list=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,id);

        preparedStatement.setString(3,name);
        preparedStatement.setString(4,name);

        preparedStatement.setString(5,college);
        preparedStatement.setString(6,college);

        preparedStatement.setString(7,status);
        preparedStatement.setString(8,status);

        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            list.add(new Teacher(set.getString("teacher_id"),set.getString("name"),set.getString("nameSpell"),set.getString("gender"),set.getString("email"),set.getString("address"),set.getString("nativePlace"),set.getString("phonenumber"),set.getString("college"),set.getString("trainstart"),set.getString("trainend"),set.getString("policyStatus"),set.getString("marrystatus"),set.getString("status")));
        }
        return list;
    }



    @Override
    public Teacher SelfQuary(String id) throws SQLException {
        Connection connection = getConnection();
        String sql = "select * from teacher where teacher_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet set = preparedStatement.executeQuery();
        if (set.next()) {
            // Assuming a constructor that matches the fetched columns
            return new Teacher(
                    set.getString("teacher_id"),
                    set.getString("name"),
                    set.getString("gender"),
                    set.getString("email"),
                    set.getString("address"),
                    set.getString("nativePlace"),
                    set.getString("college"),
                    set.getString("phonenumber"),
                    set.getString("trainstart"),
                    set.getString("trainend"),
                    set.getString("policyStatus"),
                    set.getString("marrystatus"),
                    set.getString("major"),
                    set.getString("status")
            );
        }
        return null;
    }

    @Override
    public void updateTeacherPhoneNumber(String teacherId, String newPhoneNumber) throws SQLException {
        String sql="update teacher set phonenumber=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newPhoneNumber);
        preparedStatement.setString(2,teacherId);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }

    @Override
    public void updateTeacherEmail(String teacherId, String newEmail) throws SQLException {
        String sql="update teacher set email=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newEmail);
        preparedStatement.setString(2,teacherId);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }
    @Override
    public void updateTeacherAddress(String teacherId, String newAddress) throws SQLException {
        String sql="update teacher set address=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newAddress);
        preparedStatement.setString(2,teacherId);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }
    @Override
    public void updateTeacherTrainStart(String teacherId, String trainstart) throws SQLException {
        String sql="update teacher set trainstart=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,trainstart);
        preparedStatement.setString(2,teacherId);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }
    @Override
    public void updateTeacherTrainEnd(String teacherId, String trainend) throws SQLException {
        String sql="update teacher set trainend=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,trainend);
        preparedStatement.setString(2,teacherId);
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }

}
