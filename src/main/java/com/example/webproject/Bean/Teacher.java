package com.example.webproject.Bean;

import com.example.webproject.Bean.People;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.sourceforge.pinyin4j.PinyinHelper;

@Data
@AllArgsConstructor
public class Teacher extends People {
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
//    老师职位
    private String status;
    protected String namespell;
    public Teacher(String id,String name,String gender, String email, String address, String nativePlace,String college,String phonenumber, String trainstart,String trainend,String policyStatus,String marrystatus, String major, String status) {
        super(  id,
                name,
                name,
                gender,
                email,
                address,
                nativePlace,
                phonenumber,
                college,
                trainstart,
                trainend,
                policyStatus,
                marrystatus,
                major);
        super.setSpellname(convertToPinyin(name));
        this.status=status;

    }
}
