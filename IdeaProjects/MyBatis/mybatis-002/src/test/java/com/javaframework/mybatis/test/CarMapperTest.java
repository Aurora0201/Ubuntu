package com.javaframework.mybatis.test;

import com.javaframework.mybatis.bean.Account;
import com.javaframework.mybatis.utils.SessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void selectTest() {
        SqlSession sqlSession = SessionUtil.getSqlSession();
        Account account = (Account) sqlSession.selectOne("selectByActno", "binjunkai");
        System.out.println(account);
    }

}

