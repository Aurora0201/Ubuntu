package com.framework.spring.test;

import com.framework.spring.dao.DaYe;
import com.framework.spring.dao.DataSource;
import com.framework.spring.dao.ListAndSet;
import com.framework.spring.dao.UserDao;
import com.framework.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITest {
    @Test
    public void DITest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userServiceBean = applicationContext.getBean("userService", UserService.class);

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
    public void ListAndSetTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ListAndSet.xml");
        ListAndSet listAndSet = applicationContext.getBean("listAndSet", ListAndSet.class);
        System.out.println(listAndSet);
    }

    @Test
    public void DataTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ds.xml");
        DataSource dataSource = applicationContext.getBean("dataSource", DataSource.class);
        DataSource dataSource1 = applicationContext.getBean("dataSource", DataSource.class);
        DataSource dataSource2 = applicationContext.getBean("dataSource", DataSource.class);

        System.out.println(dataSource);
        System.out.println(dataSource1);
        System.out.println(dataSource2);
    }
}
