package JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCUserTest {
    public static void main(String[] args) {
        Map<String, String> info = User.initUI();
        boolean success = User.login(info);

        /*
        * 1.SQL injection
        * Please input a username:any username
        * Please input a password:any username' or '1' = '1
        * Login successful
        *
        * 2.What is the SQL injection
        * The information user entered become part of sql statement,
        * modifying the original meaning of the sql statement.
        *
        * 3.How to solve the problem?
        * Use preparedStatement
        * ---> UserImprovedEdition
        *
        * 4.When should we use "Statement"?
        * When we need to combine SQL statement or
        * the service need SQL injection
        *
        * */

        System.out.println("Login " + (success ? "successful" : "failed"));
    }
}

class User{

    public static boolean login(Map<String, String> info) {
        String loginName = info.get("loginName");
        String loginPwd = info.get("loginPwd");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("JDBC/JDBC");
            Class.forName(bundle.getString("className"));

            conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("password"));
            stmt = conn.createStatement();
            String sql = "select * from user where loginName = '" + loginName + "' and loginPwd = '" + loginPwd + "'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) loginSuccess = true;


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
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
        return loginSuccess;
    }

    public static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("Please input a username:");
        String userName = s.nextLine();
//        System.out.println();

        System.out.print("Please input a password:");


        String password = s.nextLine();

        HashMap<String, String> mp= new HashMap<String, String>();
        mp.put("loginName", userName);
        mp.put("loginPwd", password);
        return mp;
    }
}