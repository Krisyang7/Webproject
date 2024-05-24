package com.example.webproject.Bean;

import com.example.webproject.Bean.People;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher extends People {
//    老师职位
    private String status;
    protected String namespell;
    public Teacher(String id,String name,  String gender, String email, String address, String nativePlace,String college,String phonenumber, String academy, String trainstart,String trainend,String policyStatus,String nameSpell, String marrystatus, String major, String status) {
        super(id,name, gender, email, address,nativePlace,phonenumber, academy,trainstart,trainend,policyStatus,marrystatus,major);
        this.status=status;
    }
}
