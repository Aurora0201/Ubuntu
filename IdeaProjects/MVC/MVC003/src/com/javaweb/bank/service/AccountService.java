package com.javaweb.bank.service;

import com.javaweb.bank.bean.Account;
import com.javaweb.bank.utils.DBTool;
import com.javaweb.bank.dao.AccountDAO;
import com.javaweb.bank.exceptions.AppException;
import com.javaweb.bank.exceptions.MoneyNotEnoughException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Account service
 * A class specially handle service of account
 * Service class always called XxxService,XxxBiz
 * @author binjunkai
 * @version 2.0
 * @since 2.0
 */
public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();
    public void transfer(String fromActno, String toActno, double money) throws AppException, MoneyNotEnoughException{
        try (Connection conn = DBTool.getConnection()) {
            conn.setAutoCommit(false);
            Account fromAct = accountDAO.selectByActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException("You do not have enough money!");
            }
            Account toAct = accountDAO.selectByActno(toActno);
            fromAct.setBalance(fromAct.getBalance() - money);


            toAct.setBalance(toAct.getBalance() + money);

            int count = accountDAO.update(fromAct);
            count += accountDAO.update(toAct);
            if (count != 2) {
                throw new AppException("Transfer Exception Error");
            }
            conn.commit();
        } catch (SQLException e) {
            throw new AppException("Transfer Exception Error");
        }
    }
}
