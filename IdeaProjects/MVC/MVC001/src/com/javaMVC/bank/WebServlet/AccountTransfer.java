package com.javaMVC.bank.WebServlet;

import com.javaMVC.bank.Exception.AppException;
import com.javaMVC.bank.Exception.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Transfer
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountTransfer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String fromAct = request.getParameter("fromAct");
        String toAct = request.getParameter("toAct");
        double money = Double.parseDouble(request.getParameter("money"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mvc";
            String name = "root";
            String password = "root1234";
            conn = DriverManager.getConnection(url, name, password);
            conn.setAutoCommit(false);

            String sql = "select * from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, fromAct);
            rs = ps.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < money) {
                    throw new MoneyNotEnoughException("You do not have enough money to transfer");
                }
                String upd = "update t_act set balance = balance - ? where actno = ?";
                String upd1 = "update t_act set balance = balance + ? where actno = ?";

                ps = conn.prepareStatement(upd);
                ps.setDouble(1, money);
                ps.setString(2, fromAct);
                int count = ps.executeUpdate();

                ps = conn.prepareStatement(upd1);
                ps.setDouble(1, money);
                ps.setString(2, toAct);
                count += ps.executeUpdate();

                if (count != 2) {
                    throw new AppException("App Error found");
                }else out.print("Transfer successful");
                conn.commit();
            }
        } catch (ClassNotFoundException | SQLException | MoneyNotEnoughException | AppException e) {
            out.print(e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }finally{
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

    }
}
