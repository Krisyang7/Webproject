package com.example.webproject.Daos;

import com.example.webproject.Bean.Student;
import com.example.webproject.Bean.Update;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public interface Manager_update extends Dao{
    void commit_update(HttpServletRequest request) throws SQLException;

    List<Update> get_commit() throws SQLException;

    void whether_update(String action,String update_id) throws SQLException;
}
