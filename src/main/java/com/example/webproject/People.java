package com.example.webproject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {
    protected String id;
    protected String name;
    protected String password;
    protected String gender;
    protected String email;
    protected String address;//出生地
    protected String nativePlace;//籍贯
    protected String phonenumber;
    protected String academy;
    protected String trainstart;
    protected String trainend;
    protected String policyStatus;
    protected String nameSpell;
    protected String marrystatus;
    // 添加无参构造函数
    public People() {
        // 空的构造函数
    }
}
