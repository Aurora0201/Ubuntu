package JDBC;

import java.sql.*;
import java.util.ResourceBundle;

public class UserUpdate {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ResourceBundle bundle = ResourceBundle.getBundle("JDBC/JDBC");
        String className = bundle.getString("className");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");


        Class.forName(className);
        conn = DriverManager.getConnection(url, user, password);
//        String sql1 = "insert into user(loginName,loginPwd,realName) values('root','root','root用户')";
//        String sql1 = "alter table user RENAME to t_user";
//        ps = conn.prepareStatement(sql1);
//        int retVal = ps.executeUpdate();
//        System.out.println("Update " + (retVal == 1 ? "successful" : "failed"));
        String sql = "select * from t_user order by loginName";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("realname"));
        }
        rs.close();
        ps.close();
        conn.close();
    }
}
