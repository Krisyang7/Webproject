package com.example.webproject.Daos;

import java.sql.SQLException;

public interface LoginDao extends Dao {
    void UpdatePassword(String id,String password) throws SQLException;
    String GetPassword(String id) throws SQLException;




}
