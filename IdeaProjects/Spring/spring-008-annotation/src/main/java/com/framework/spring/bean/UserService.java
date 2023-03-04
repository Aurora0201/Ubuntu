package com.framework.spring.bean;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserService() {
        System.out.println("UserService instantiation");
    }
}
