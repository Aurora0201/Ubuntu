package com.javaweb.bank.service.proxy;


import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Object getProxy(Object target) {
        Object proxy = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new TransactionInvocation(target)
        );
        return proxy;
    }
}
