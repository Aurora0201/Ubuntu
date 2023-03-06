package com.framework.ss.test;

import com.framework.ss.service.AccountService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class SSTest {
    @Autowired
    AccountService accountService;
    @Test
    public void transferTest() {
        accountService.transfer("binjunkai","zhangsan",100);
    }
}
