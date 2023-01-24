package JDBC.Util;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {

    static ResourceBundle bundle = null;
    /*
    * Usually the constructor of utility class with modifier "private"
    * because the methods in the class are all static.
    * */

    private JDBCUtil() {

    }
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        bundle = ResourceBundle.getBundle("JDBC/JDBC");
    }

    /**
     * Get the connection
     * @return the connection of the database
     * @throws SQLException Exception
     */
    public static Connection getConnection() throws SQLException {
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Close the resources
     * @param conn Connection
     * @param stmt Statement
     * @param rs ResultSet
     */
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
