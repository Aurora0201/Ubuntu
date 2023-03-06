package com.framework.spring.test;

import com.framework.spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:spring.xml")
public class JUnit5Test {
    @Autowired
    private UserService userService;
    @Test
    public void test1() {
        userService.logout();
    }
}
