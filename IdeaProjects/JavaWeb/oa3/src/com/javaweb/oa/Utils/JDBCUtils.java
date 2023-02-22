package com.javaweb.oa.Utils;
import java.sql.*;
import java.util.ResourceBundle;
/*
* JDBC Utils
* */
public class JDBCUtils {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static String driver;
    private static String url;
    private static String name;
    private static String password;
    static {
        driver = bundle.getString("driver");
        url = bundle.getString("url");
        name = bundle.getString("name");
        password = bundle.getString("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, name, password);
    }
    public static void close(Connection conn ,PreparedStatement ps, ResultSet rs){

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
