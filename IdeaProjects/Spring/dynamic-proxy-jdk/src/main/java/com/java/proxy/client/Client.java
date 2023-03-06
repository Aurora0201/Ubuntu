package com.java.proxy.client;

import com.java.proxy.service.OrderService;
import com.java.proxy.service.OrderServiceImpl;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        //create target program
        OrderService target = new OrderServiceImpl();
        //create proxy program
        OrderService orderService = (OrderService) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new TimeInvocationHandle(target)
        );

        orderService.generate();
        String details = orderService.details();
//        System.out.println(details);
        String modify = orderService.modify("123");
//        System.out.println(modify);
    }
}
