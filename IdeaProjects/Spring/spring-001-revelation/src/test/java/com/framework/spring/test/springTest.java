package com.framework.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Target;

public class springTest {
    @Test
    public void beanTest() {
        //get spring application context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");


        Object userBean = applicationContext.getBean("userBean");
        System.out.println(userBean);

        Object userBeanDao = applicationContext.getBean("userBeanDao");
        System.out.println(userBeanDao);
    }
}
