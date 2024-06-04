package com.example.webproject.Daos;

import com.example.webproject.Bean.Diary;

import java.util.List;

public interface DiaryDao extends Dao{
    public List<Diary> getAllDiaries();

}
