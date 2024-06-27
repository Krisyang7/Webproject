package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Teacher;
import com.example.webproject.Daos.TeacherDao;
import com.example.webproject.SM.SM2Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TeacherImpl implements TeacherDao {

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
    public List<Student> MentorQuary(String id) {
        String sql="select * from students where mentor=?";
        List<Student> list=new ArrayList<>();

        try {
            Connection connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()){
                list.add(new Student(set.getString("student_id"),set.getString("name"),set.getString("gender"),set.getString("email"),set.getString("address"),set.getString("nativePlace"),set.getString("phonenumber"),set.getString("college"),set.getString("trainstart"),set.getString("trainend"),set.getString("policyStatus"),set.getString("marrystatus"),set.getString("mentor"),set.getString("major"),set.getString("degree")));
            }
            TeacherImpl teacher=new TeacherImpl();
            String diary="MentorQuary where mentor="+id;
            teacher.SetDiary(id,diary);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Student> QuaryStudent(String oid,String id,String name,String mentor,String nativePlace,String college,String major ) throws SQLException {
        String sql = "SELECT * FROM students " +
                "WHERE " +
                "    (student_id = ? OR ? = '') " + // 如果student_id参数为空字符串，则始终为真
                "    AND (name LIKE CONCAT('%', ?, '%') OR ? = '') " + // 如果name参数为空字符串，则不应用过滤条件
                "    AND (mentor =? OR ? = '') " +
                "    AND (nativePlace = ? OR ? = '') " +
                "    AND (college LIKE CONCAT('%', ?, '%') OR ? = '') " +
                "    AND (major =? OR ? = '')";
        List<Student> list=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,id);

        preparedStatement.setString(3,name);
        preparedStatement.setString(4,name);

        preparedStatement.setString(5, mentor.isEmpty() ? "" : SM2Utils.encrypt(mentor));
        preparedStatement.setString(6,mentor);

        preparedStatement.setString(7,nativePlace.isEmpty()?"":SM2Utils.encrypt(nativePlace));
        preparedStatement.setString(8,nativePlace);

        preparedStatement.setString(9,college);
        preparedStatement.setString(10,college);

        preparedStatement.setString(11,major.isEmpty()?"":SM2Utils.encrypt(major));
        preparedStatement.setString(12,major);

        ResultSet set = preparedStatement.executeQuery();
        while (set.next()){
            String a=set.getString("student_id");
            String b=set.getString("name");
            String c=set.getString("gender");
            String d=SM2Utils.decrypt(set.getString("email"));
            String e=SM2Utils.decrypt(set.getString("address"));
            String f=SM2Utils.decrypt(set.getString("nativePlace"));
            String g=SM2Utils.decrypt(set.getString("phonenumber"));
            String h=set.getString("college");
            String i=set.getString("trainstart");
            String j=set.getString("trainend");
            String k=set.getString("policyStatus");
            String l=set.getString("marrystatus");
            String m=SM2Utils.decrypt(set.getString("mentor"));
            String n=SM2Utils.decrypt(set.getString("major"));
            String o=set.getString("degree");
            list.add(new Student(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o));
            if (list.size()==998){
                System.out.println(1);
            }
        }
        TeacherImpl teacher=new TeacherImpl();
        String sqla="Select * from students where id="+id+" and name="+name+" and mentor"+mentor+" and nativePlace"+nativePlace+" and college"+college+" and major"+major;
        teacher.SetDiary(oid,sqla);
        return list;
    }

    public List<Teacher> QuaryTeacher(String oid,String id,String name,String college,String status ) throws SQLException {
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
            list.add(new Teacher(set.getString("teacher_id"),set.getString("name"),set.getString("nameSpell"),set.getString("gender"),SM2Utils.decrypt(set.getString("email")),SM2Utils.decrypt(set.getString("address")),SM2Utils.decrypt(set.getString("nativePlace")),SM2Utils.decrypt(set.getString("phonenumber")),set.getString("college"),set.getString("trainstart"),set.getString("trainend"),set.getString("policyStatus"),set.getString("marrystatus"),SM2Utils.decrypt(set.getString("status"))));
        }
        TeacherImpl teacher=new TeacherImpl();
        String diary="select * from teacher where id="+id+" and name="+name+" and college="+college+" and status"+status;
        teacher.SetDiary(oid,diary);
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
                    SM2Utils.decrypt(set.getString("email")) ,
                    SM2Utils.decrypt(set.getString("address")) ,
                    SM2Utils.decrypt(set.getString("nativePlace")),
                    set.getString("college"),
                    SM2Utils.decrypt(set.getString("phonenumber")) ,
                    set.getString("trainstart"),
                    set.getString("trainend"),
                    set.getString("policyStatus"),
                    set.getString("marrystatus"),
                    set.getString("major"),
                    set.getString("status")
            );
        }
        TeacherImpl teacher=new TeacherImpl();
        teacher.SetDiary(id,"SelfQuary");
        return null;
    }

    @Override
    public void updateTeacherPhoneNumber(String teacherId, String newPhoneNumber) throws SQLException {
        String sql="update teacher set phonenumber=? where teacher_id=?";
        Connection connection=getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,SM2Utils.encrypt(newPhoneNumber) );
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
        preparedStatement.setString(1,SM2Utils.encrypt(newEmail) );
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
        preparedStatement.setString(1,SM2Utils.encrypt(newAddress) );
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
