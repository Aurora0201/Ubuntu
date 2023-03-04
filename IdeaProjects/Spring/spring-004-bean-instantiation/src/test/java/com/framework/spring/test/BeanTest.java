package com.framework.spring.test;

import com.framework.spring.bean.MyTime;
import com.framework.spring.bean.Star;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
    @Test
    public void getBean2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Star star = applicationContext.getBean("star", Star.class);
        System.out.println(star);
    }

    @Test
    public void getBean3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Star star = applicationContext.getBean("star1", Star.class);
        System.out.println(star);
    }
    @Test
    public void getBean4() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        MyTime myTime = applicationContext.getBean("myTime", MyTime.class);
        System.out.println(myTime);

    }
}
