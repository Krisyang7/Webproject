package com.example.webproject.Bean;

import com.example.webproject.Bean.People;
import lombok.Data;


@Data
public class Student extends People {
    public Student(String id,String name, String gender, String email, String address, String nativePlace,String college,String phonenumber, String academy, String trainstart,String trainend,String policyStatus,String nameSpell, String marrystatus,String mentor, String major, String degree) {
        super(id,name, gender, email, address,nativePlace,college,phonenumber, academy,trainstart,trainend,policyStatus,nameSpell,marrystatus,major);
        this.mentor = mentor;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mentor='" + mentor + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", id='" + id + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", academy='" + academy + '\'' +
                '}';
    }

    private String mentor;//导师
    private String degree;//学位
}
