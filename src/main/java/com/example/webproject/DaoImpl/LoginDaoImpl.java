package com.example.webproject.DaoImpl;

import com.example.webproject.Bean.Student;
import com.example.webproject.Daos.LoginDao;
import com.example.webproject.SM.SM3Utils;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LoginDaoImpl implements LoginDao {
    public void SetDiary(String name,String sql){
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            String s="insert into diary values (?,?,?)";
            Connection connection=getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,name);
            preparedStatement.setString(3,formattedDateTime);
            preparedStatement.setString(2,sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void UpdatePassword(String id,String password) throws SQLException {
        Connection conn = getConnection();
        Date now=new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        PreparedStatement stmt = conn.prepareStatement("UPDATE login SET password = ? ,lastPasswordChangeDate= ? WHERE id = ?");
        stmt.setString(1, SM3Utils.encrypt(password) );
        stmt.setString(3, id);
        stmt.setDate(2,sqlDate);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        LoginDaoImpl loginDao=new LoginDaoImpl();
        String dairy="update password="+password;
        loginDao.SetDiary(id,dairy);
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


    public boolean isAccountLocked(String userid) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "SELECT locked FROM login WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Timestamp lockTime = resultSet.getTimestamp("locked");
                        if (lockTime == null) return false;
                        long lockDuration = 30 * 60 * 1000; // 锁定时长为30分钟
                        System.out.println(System.currentTimeMillis() - lockTime.getTime());
                        return System.currentTimeMillis() - lockTime.getTime() < lockDuration;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
        conn.close();
        return false;
    }

    // 重置失败登录尝试次数的方法
    public void resetFailedLoginAttempts(String userid) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "UPDATE login SET attempt = 0, locked = NULL WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
        conn.close();
    }

    // 锁定账户
    public void lockAccount(String userid) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "UPDATE login SET locked = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(2, userid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
        conn.close();
    }

    // 更新失败登录尝试次数
    public void updateFailedLoginAttempts(String userid, int attempts) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "UPDATE login SET attempt = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, attempts);
                preparedStatement.setString(2, userid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
        conn.close();
    }


    public void createFailedLoginAttemptRecord(String userid) throws SQLException {
        Connection conn = getConnection();
        try {
            String sql = "INSERT INTO login (id, attempt) VALUES (?, 1)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, userid);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("数据库操作错误", e);
        }
        conn.close();
    }


}
