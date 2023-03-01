package com.framework.spring.service;

import com.framework.spring.dao.UserDao;
import com.framework.spring.dao.VipDao;

public class UserService {
    private UserDao userDao;
    private VipDao vipDao;

    public UserService(UserDao userDao, VipDao vipDao) {
        this.userDao = userDao;
        this.vipDao = vipDao;
    }

    public UserService() {

    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setVipDao(VipDao vipDao) {
        this.vipDao = vipDao;
    }

    public void insert() {
        userDao.insert();
        vipDao.insert();
    }
}
