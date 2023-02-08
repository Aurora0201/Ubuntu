package com.javaweb.bank.dao;


import com.javaweb.bank.bean.Account;
import com.javaweb.bank.utils.DBTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Account data access object
 * @author binjunkai
 * @version 2.0
 * @since 2.0
 */
public class AccountDAO {
    /**
     * insert data into DB
     * @param act actno
     * @return 1 represents success
     */
    public int insert(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int cot = 0;
        try {
            conn = DBTool.getConnection();
            String sql = "insert into t_act(actno,balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, act.getActno());
            ps.setDouble(2, act.getBalance());
            cot = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBTool.close(conn,ps,null);
        }
        return cot;
    }

    /**
     * delete act by id
     * @param id actId
     * @return 1 represents success
     */
    public int delById(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int cot = 0;
        try {
            conn = DBTool.getConnection();
            String sql = "delete from t_act where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            cot = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            DBTool.close(conn, ps, null);
        }

        return cot;
    }

    /**
     * update DB
     * @param act the account need to be updated
     * @return 1 represents success
     */
    public int update(Account act) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBTool.getConnection();
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, act.getBalance());
            ps.setString(2, act.getActno());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBTool.close(conn, ps, null);
        }
        return count;
    }

    /**
     * select item from DB
     * @param actno actno
     * @return Account
     */
    public Account selectByActno(String actno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account act = null;
        try {
            conn = DBTool.getConnection();
            String sql = "select * from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actno);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                act = new Account(id, actno, balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBTool.close(conn, ps, rs);
        }
        return act;
    }

    /**
     * select all items from DB
     * @return List of Account
     */
    public ArrayList<Account> selectAll() {
        return null;
    }
}
