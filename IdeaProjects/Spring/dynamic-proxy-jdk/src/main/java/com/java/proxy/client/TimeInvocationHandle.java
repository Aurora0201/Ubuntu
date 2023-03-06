package com.java.proxy.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeInvocationHandle implements InvocationHandler {
    private Object target;

    public TimeInvocationHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        System.out.println("proxy done!");

        Object retValue = method.invoke(target, args);

//        System.out.println(proxy);
        return retValue;
    }
}
