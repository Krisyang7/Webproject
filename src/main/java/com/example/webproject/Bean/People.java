package com.example.webproject.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People {
    protected String id;
    protected String name;//姓名
    protected String gender;//性别
    protected String email;
    protected String address;//出生地
    protected String nativePlace;//籍贯
    protected String phonenumber;//电话
    protected String academy;//学院
    protected String trainstart;//火车起始站
    protected String trainend;//火车终止站
    protected String policyStatus;//政治面貌
    protected String marrystatus;//婚姻状况
    protected String major;//专业
    // 添加无参构造函数
    public People() {
        // 空的构造函数
    }

}
