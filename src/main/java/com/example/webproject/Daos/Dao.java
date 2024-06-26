package com.example.webproject.Daos;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public interface Dao {
    public static DataSource getDataSource() throws NamingException {
        DataSource dataSource=null;
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ybcweb");
            return dataSource;
    }
    public default Connection getConnection() throws SQLException {
        DataSource dataSource= null;
        try {
            dataSource = getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        Connection conn=null;
        conn=dataSource.getConnection();
        return conn;
    }
}