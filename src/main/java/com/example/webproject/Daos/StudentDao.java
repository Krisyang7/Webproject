package com.example.webproject.Daos;

import com.example.webproject.Bean.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao extends Dao {
    Student getStudentById(String studentId) throws SQLException;

    Student getStudentByIdandcollege(String studentId,String college) throws SQLException;


    // Method to update a student's phone number
    void updateStudentPhoneNumber(String studentId, String newPhoneNumber) throws SQLException;

    // Method to update a student's email address
    void updateStudentEmail(String studentId, String newEmail) throws SQLException;

     List<Student> search_college(String academy) throws SQLException;

}
