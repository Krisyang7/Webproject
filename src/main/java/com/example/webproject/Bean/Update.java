package com.example.webproject.Bean;

import lombok.Data;

import java.awt.*;

@Data
public class Update extends Student{
    public Update(String id, String name, String gender, String email, String address, String nativePlace, String phonenumber, String coleege, String trainstart, String trainend, String policyStatus, String marrystatus, String mentor, String major, String degree, String judgeing, String update_id, String submitted_by, String submitted_at){
        super(id,name,  gender,  email,  address,  nativePlace, phonenumber,  coleege,  trainstart, trainend, policyStatus, marrystatus, mentor,  major,  degree, judgeing);
        this.update_id=update_id;
        this.submitted_by=submitted_by;
        this.submitted_at=submitted_at;
    }
    public Update(){}

    private String update_id;
    private String submitted_by;
    private String submitted_at;
    private Student origin;//原学生信息

}
