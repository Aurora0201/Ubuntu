package com.javaweb.bank.utils;

import com.javaweb.bank.utils.DBTool;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionAssist {
    private static Connection conn;
    private TransactionAssist(){}

    public static void startTransaction() throws SQLException {
        conn = DBTool.getConnection();
        conn.setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        conn = DBTool.getConnection();
        conn.commit();
    }

    public static void rollabck() throws SQLException {
        conn = DBTool.getConnection();
        conn.rollback();
    }
}
