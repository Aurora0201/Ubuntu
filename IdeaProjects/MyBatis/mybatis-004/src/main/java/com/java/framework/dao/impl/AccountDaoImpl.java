package com.java.framework.dao.impl;

import com.java.framework.bean.Account;
import com.java.framework.dao.AccountDao;
import com.java.framework.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {

    @Override
    public Account selectByActno(String actno) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        Account account = (Account) sqlSession.selectOne("selectByActno", actno);
        return account;
    }

    @Override
    public int updateByActno(Account account) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        int count = sqlSession.update("updateByActno", account);
        return count;
    }
}
