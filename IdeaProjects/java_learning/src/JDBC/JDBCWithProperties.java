package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCWithProperties {
    public static void main(String[] args) {
        /*
        * In actual development,
        * we should better write the config file into local file
        *
        * */
        ResourceBundle bundle = ResourceBundle.getBundle("JDBC/JDBC");
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(bundle.getString("className"));
            conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("password"));
            System.out.println(conn);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
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
}
