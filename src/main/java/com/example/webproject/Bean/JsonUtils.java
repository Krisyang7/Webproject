package com.example.webproject.Bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtils {

    public static JSONObject getJsonArrayFromObject(InputStreamReader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        try {
            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            return jsonObject;
            // 创建一个只包含这个对象的JSONArray
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.add(jsonObject);
            //return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
            // 可以在这里添加更具体的错误处理逻辑
            return null; // 或者抛出一个异常
        } finally {
            try {
                br.close(); // 确保BufferedReader被关闭
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}