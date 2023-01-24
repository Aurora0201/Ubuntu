package JDBC;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserSupplement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        * When the user enters "desc",
        * the table displays descending order otherwise it is ascending order
        * */
        Scanner s = new Scanner(System.in);
        System.out.print("Please enter desc or asc:");
        String order = s.next();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("JDBC/JDBC");
        String className = bundle.getString("className");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");


        Class.forName(className);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
        String sql = "select * from EMP order by ename " + order;
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("ename"));
        }
        rs.close();
        stmt.close();
        conn.close();
    }
}
