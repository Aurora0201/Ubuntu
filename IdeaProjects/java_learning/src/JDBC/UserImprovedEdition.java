package JDBC;

import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

/*
* Solve the SQL injection problem
* */
public class UserImprovedEdition {
    public static void main(String[] args) {
        Map<String, String> info = User.initUI();
        boolean success = UserImprovedEdition.login(info);
        System.out.println("Login " + (success ? "successful" : "failed"));
    }

    public static boolean login(Map<String, String> info) {
        String loginName = info.get("loginName");
        String loginPwd = info.get("loginPwd");
        /*
        * When we use JDBC,
        * we should turn off auto commit mechanism,
        * commit transaction at the end of "try" statement,
        * and rollback transaction when SQLException occurs
        *
        * conn.setAutoCommit(false);
        * conn.commit();
        * conn.rollback();
        * */
        Connection conn = null;

//        Statement stmt = null;
        /*
        * Use PreparedStatement instead of Statement
        * PreparedStatement will precompile the SQL statement,
        * the info input by user will be passed in as parameters.
        * */
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            //Turn off auto commit at first
            conn.setAutoCommit(false);
            ResourceBundle bundle = ResourceBundle.getBundle("JDBC/JDBC");
            Class.forName(bundle.getString("className"));
            conn = DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"), bundle.getString("password"));
            /*
            * Use '?' as a placeholder in SQL statement,
            * we can replace it with the parameters.
            *
            * We can use index to access the placeholder,
            * the index start from 1.
            * */
            String sql = "select * from user where loginName = ? and loginPwd = ?";
            ps = conn.prepareStatement(sql);
            //Replace the placeholder with String
            ps.setString(1, loginName);
            ps.setString(2, loginPwd);

            /*
            * Replace with int
            * ps.setInt(index, values)
            * */

            //Start to query
            rs = ps.executeQuery();

            if(rs.next()) loginSuccess = true;

            //If program runs here, commit the transaction otherwise rollback it.
            conn.commit();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            /*
            * Rollback transaction here
            * */
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }finally {
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
        return loginSuccess;
    }
}
