package com.framework.spring.dao;

public class Woman {
    private String name;

    public Woman() {
    }

    public Woman(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Woman{" +
                "name='" + name + '\'' +
                '}';
    }
}
