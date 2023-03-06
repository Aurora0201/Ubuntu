package com.javaweb.bank.service.proxy;

import com.javaweb.bank.utils.TransactionAssist;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransactionInvocation implements InvocationHandler {
    private Object target;

    public TransactionInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TransactionAssist.startTransaction();
        Object retValue = method.invoke(target, args);
        TransactionAssist.commit();
        return retValue;
    }
}
