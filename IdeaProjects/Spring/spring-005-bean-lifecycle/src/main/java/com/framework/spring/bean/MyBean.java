package com.framework.spring.bean;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class MyBean{
    private String name;



    public void setName(String name) {
        this.name = name;
        System.out.println("setter done!");
    }

    public MyBean() {
        System.out.println("constructor done!");
    }



    public void init() {
        System.out.println("init done!");
    }

    public void destroy() {
        System.out.println("destroy done!");
    }
}
