package com.framework.spring.test;

import com.framework.spring.dao.DaYe;
import com.framework.spring.dao.UserDao;
import com.framework.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    @Test
    public void DITest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDaoBean = applicationContext.getBean("userDaoBean", UserDao.class);
        UserService userServiceBean = applicationContext.getBean("userServiceBean", UserService.class);

        userServiceBean.insert();
    }

    @Test
    public void DITest2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDaoBean = applicationContext.getBean("userDaoBean", UserDao.class);
        UserService userServiceBean = applicationContext.getBean("userServiceBean", UserService.class);

        userServiceBean.insert();
    }

    @Test
    public void DaYeTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("DaYe.xml");
        DaYe daYeBean = applicationContext.getBean("DaYeBean", DaYe.class);
        System.out.println(daYeBean);
    }

    @Test
    public void phpTest() {

    }
}
