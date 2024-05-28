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
        String nameF = pinyinBuilder.toString(); // 假设pinyinBuilder是一个StringBuilder对象
        if (!nameF.isEmpty()) {
            nameF = Character.toUpperCase(nameF.charAt(0)) + nameF.substring(1);
        }
        StringBuilder nameFWithSpaces = new StringBuilder(nameF);
        for (int i = 0; i < nameFWithSpaces.length(); i++) {
            if (Character.isDigit(nameFWithSpaces.charAt(i))) {
                nameFWithSpaces.setCharAt(i, ' '); // 将数字替换为空格
            }
        }

// 获取最终的字符串
        String finalNameF = nameFWithSpaces.toString();
        return finalNameF;
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
