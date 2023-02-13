package com.javaweb.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC Utils
 * Provide a convenient way to use MySQL
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class DBTool {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");

    private static String url = bundle.getString("url");
    private static String driver = bundle.getString("driver");
    private static String name = bundle.getString("name");
    private static String password = bundle.getString("password");

    private static ThreadLocal<Connection> local = new ThreadLocal<>();
    //Avoid creating instance
    private DBTool() {

    }

    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a new connection object
     * @return Connection
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url, name, password);
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if (conn != null) {
            try {
                conn.close();
                //Release connection
                local.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
