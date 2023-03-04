package com.framework.spring.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class User {
    public User() {
        System.out.println("User instantiation");
    }
}
