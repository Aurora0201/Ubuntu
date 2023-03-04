package com.framework.spring.test;

import com.framework.spring.bean.MyBean;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
    @Test
    public void LifeTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        MyBean myBean = applicationContext.getBean("myBean", MyBean.class);

        System.out.println(myBean);

        //close application
        ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) applicationContext;
        context.close();
    }

    @Test
    public void registerTest() {
        MyBean myBean = new MyBean();
        System.out.println(myBean);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerSingleton("myBean",myBean);

        System.out.println(factory.getBean("myBean"));
    }
}
