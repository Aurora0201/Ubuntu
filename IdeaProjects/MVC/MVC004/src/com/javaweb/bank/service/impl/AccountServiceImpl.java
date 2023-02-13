package com.javaweb.bank.service.impl;

import com.javaweb.bank.bean.Account;
import com.javaweb.bank.service.AccountService;
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
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO = new AccountDAO();
    public void transfer(String fromActno, String toActno, double money) throws AppException, MoneyNotEnoughException{
        Account fromAct = accountDAO.selectByActno(fromActno);
        if (fromAct.getBalance() < money) {
            throw new MoneyNotEnoughException("You do not have enough money!");
        }
        Account toAct = accountDAO.selectByActno(toActno);
        fromAct.setBalance(fromAct.getBalance() - money);


//        String s = null;
//        s.toString();

        toAct.setBalance(toAct.getBalance() + money);

        int count = accountDAO.update(fromAct);
        count += accountDAO.update(toAct);
        if (count != 2) {
            throw new AppException("Transfer Exception Error");
        }

    }
}
