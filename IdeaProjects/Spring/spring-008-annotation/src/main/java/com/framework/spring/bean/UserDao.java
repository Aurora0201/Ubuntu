package com.framework.spring.bean;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public UserDao() {
        System.out.println("UserDao instantiation");
    }
}
