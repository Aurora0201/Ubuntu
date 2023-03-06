package com.framework.spring.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void login() {
        System.out.println("user login!");
    }

    public void logout() {
        System.out.println("user logout!");
    }
}
