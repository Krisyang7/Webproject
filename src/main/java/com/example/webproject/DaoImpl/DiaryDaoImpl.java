package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Diary;
import com.example.webproject.Daos.DiaryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryDaoImpl implements DiaryDao {

    public List<Diary> getAllDiaries() {
        List<Diary> diaries = new ArrayList<>();
        String sql = "SELECT operator, action, actiontime FROM diary";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Diary diary = new Diary();
                diary.setOperator(resultSet.getString("operator"));
                diary.setAction(resultSet.getString("action"));
                diary.setActionTime(resultSet.getTime("actiontime"));
                diaries.add(diary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diaries;
    }
}
