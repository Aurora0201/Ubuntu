package com.myspring.test;

import com.framework.spring.bean.UserService;
import org.junit.Test;
import org.myspring.core.ApplicationContext;
import org.myspring.core.ClassPathXmlApplicationContext;

public class MySpringTest {
    @Test
    public void parseTest() {
        ApplicationContext context = new ClassPathXmlApplicationContext("myspring.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.save();
    }
}
