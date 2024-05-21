package com.example.webproject;

import lombok.Data;


@Data
public class Student extends People{
    public Student(String id,String name, String password, String gender, String email, String address, String nativePlace,String phonenumber, String academy, String trainstart,String trainend,String policyStatus,String nameSpell, String marrystatus,String mentor, String major, String degree) {
        super(id,name, password, gender, email, address,nativePlace,phonenumber, academy,trainstart,trainend,policyStatus,nameSpell,marrystatus);
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
