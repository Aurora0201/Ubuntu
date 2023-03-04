package com.framework.spring.test;

import com.framework.spring.bean.Husband;
import com.framework.spring.bean.Wife;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependencyTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Husband husband = context.getBean("husband", Husband.class);
    }
}
