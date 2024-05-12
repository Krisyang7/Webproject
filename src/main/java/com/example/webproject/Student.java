package com.example.webproject;

import lombok.Data;


@Data
public class Student extends People{
    public Student(String id, String password, String gender, String email, String address, String phonenumber, String academy, String mentor, String major, String degree) {
        super(id, password, gender, email, address, phonenumber, academy);
        this.mentor = mentor;
        this.major = major;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mentor='" + mentor + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", academy='" + academy + '\'' +
                '}';
    }

    private String mentor;
    private String major;
    private String degree;
}
