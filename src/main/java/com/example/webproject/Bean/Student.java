package com.example.webproject.Bean;

import com.example.webproject.Bean.People;
import lombok.Data;
import net.sourceforge.pinyin4j.PinyinHelper;


@Data
public class Student extends People {
    public static String convertToPinyin(String name) {
        StringBuilder pinyinBuilder = new StringBuilder();
        for (char c : name.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                pinyinBuilder.append(pinyinArray[0]);
            } else {
                pinyinBuilder.append(c);
            }
        }
        return pinyinBuilder.toString();
    }

    public Student(String id,String name, String gender, String email, String address, String nativePlace,String phonenumber, String academy, String trainstart,String trainend,String policyStatus,String marrystatus,String mentor, String major, String degree) {
        super(id,name,name, gender, email, address,nativePlace,phonenumber, academy,trainstart,trainend,policyStatus,marrystatus,major);
        super.setSpellname(convertToPinyin(name));
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
                ", college='" + college + '\'' +
                '}';
    }

    private String mentor;//导师
    private String degree;//学位
}
