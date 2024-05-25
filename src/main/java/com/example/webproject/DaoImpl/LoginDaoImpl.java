package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Daos.LoginDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {
    @Override
    public void UpdatePassword(String id,String password) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE login SET password = ? WHERE id = ?");
        stmt.setString(1, password);
        stmt.setString(2, id);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public String GetPassword(String id) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM login WHERE id = ?");
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
          return rs.getString("password");
        }
        rs.close();
        stmt.close();
        conn.close();
        return null;
    }
}
