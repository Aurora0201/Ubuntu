package top.pi1grim.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    private DBUtil() {

    }

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("conf/jdbc");
        driver = bundle.getString("jdbc.driver");
        url = bundle.getString("jdbc.url");
        username = bundle.getString("jdbc.username");
        password = bundle.getString("jdbc.password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (st != null) {
            try {
                st.close();
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
