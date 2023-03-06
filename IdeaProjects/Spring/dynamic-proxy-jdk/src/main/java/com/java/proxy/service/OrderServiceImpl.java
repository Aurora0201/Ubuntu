package com.java.proxy.service;

public class OrderServiceImpl implements OrderService{
    @Override
    public void generate() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("generate done");
    }

    @Override
    public String details() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ZhangSan";
    }

    @Override
    public String modify(String content) {
        return "Modify done" + content;
    }
}
