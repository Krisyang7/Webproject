package com.example.webproject.Bean;

import com.example.webproject.Bean.People;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher extends People {
//    老师职位
    private String status;

    public Teacher(String id,String name,  String gender, String email, String address, String nativePlace,String college,String phonenumber, String academy, String trainstart,String trainend,String policyStatus,String nameSpell, String marrystatus, String major, String status) {
        super(id,name, gender, email, address,nativePlace,college,phonenumber, academy,trainstart,trainend,policyStatus,nameSpell,marrystatus,major);
        this.status=status;
    }
}
