package com.java.framework.service.impl;

import com.java.framework.bean.Account;
import com.java.framework.dao.AccountDao;
import com.java.framework.dao.impl.AccountDaoImpl;
import com.java.framework.exception.MoneyNotEnoughException;
import com.java.framework.exception.TransferException;
import com.java.framework.service.AccountService;
import com.java.framework.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * The implementation of AccountService
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();
    @Override
    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException {
        //get the SqlSession object
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        //judge whether the account balance is sufficient(select)
        Account fromAct = accountDao.selectByActno(fromActno);

        //insufficient balance
        if (fromAct.getBalance() < money) {
            throw new MoneyNotEnoughException("insufficient balance!");
        }
        //update java object
        int a = 1;
        Account toAct = accountDao.selectByActno(toActno);

        fromAct.setBalance(fromAct.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        //update database(update)
        int count = accountDao.updateByActno(fromAct);

//        //error occurrence simulation
//        String s = null;
//        s.toString();

        count += accountDao.updateByActno(toAct);
        if (count != 2) {
            throw new TransferException("Transfer system error!");
        }
        //commit
        sqlSession.commit();
//        close SqlSession
        SqlSessionUtil.close(sqlSession);
    }
}
