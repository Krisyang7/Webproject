package com.example.webproject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {
    protected String id;
    protected String password;
    protected String gender;
    protected String email;
    protected String address;
    protected String phonenumber;
    protected String academy;

    // 添加无参构造函数
    public People() {
        // 空的构造函数
    }
    public People(String id, String password, String gender, String email, String address, String phonenumber){
        this.id=id;
        this.password=password;
        this.gender=gender;
        this.email=email;
        this.address=address;
        this.phonenumber=phonenumber;
    }
}
