package com.framework.spring.test;

import com.framework.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AOPTest {
    @Autowired
    private UserService userService;

    @Test
    public void beforeTest() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        UserService userService = context.getBean("userService", UserService.class);
        userService.login();
//        userService.logout();
    }

    @Test
    public void annotationTest() {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        UserService userService = context.getBean("userService", UserService.class);
        userService.logout();
    }
}
