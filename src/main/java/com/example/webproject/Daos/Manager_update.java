package com.example.webproject.Daos;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Manager_update extends Dao{
    void commit_update(HttpServletRequest request) throws SQLException;
}
