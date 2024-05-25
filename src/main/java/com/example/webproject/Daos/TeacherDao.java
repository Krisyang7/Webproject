package com.example.webproject.Daos;

import com.example.webproject.Bean.Teacher;

import java.sql.SQLException;

public interface TeacherDao extends Dao {
    boolean QuaryStudent() throws SQLException;
    Teacher SelfQuary(String id) throws SQLException;
    void updateTeacherPhoneNumber(String teacherId, String newPhoneNumber) throws SQLException;

    // Method to update a student's email address
    void updateTeacherEmail(String teacherId, String newEmail) throws SQLException;
    void updateTeacherTrainStart(String id,String trainstart) throws SQLException;
    void updateTeacherTrainEnd(String id,String trainend) throws SQLException;

    void updateTeacherAddress(String id,String trainend) throws SQLException;

}
