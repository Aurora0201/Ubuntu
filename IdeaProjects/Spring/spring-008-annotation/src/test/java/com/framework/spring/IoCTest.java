package com.framework.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IoCTest {
    @Test
    public void IoCTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Object user = context.getBean("user");
        System.out.println(user);

    }

    @Test
    public void partTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    }
}
