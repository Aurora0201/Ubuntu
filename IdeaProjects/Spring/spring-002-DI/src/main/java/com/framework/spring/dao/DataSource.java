package com.framework.spring.dao;

import java.util.Properties;

public class DataSource {
    private String driver;
    private String url;
    private String username;
    private String password;



    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
