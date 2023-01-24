package JDBC;

import JDBC.Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
* Tasks:
* 1.Test Util
* 2.Implement fuzzy query
* */
public class UtilTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from t_user where loginName like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%r%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close(conn, ps, rs);
        }
    }
}
