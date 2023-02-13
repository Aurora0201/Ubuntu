package com.javaweb.bank.service.proxy;

import com.javaweb.bank.exceptions.AppException;
import com.javaweb.bank.exceptions.MoneyNotEnoughException;
import com.javaweb.bank.service.AccountService;
import com.javaweb.bank.service.impl.AccountServiceImpl;
import com.javaweb.bank.utils.TransactionAssist;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class ProxyUtil {
    public static void doProxy(String fromActno, String toActno, double money) throws AppException, MoneyNotEnoughException{
        AccountService accountService = (AccountService) Proxy.newProxyInstance(
            AccountServiceImpl.class.getClassLoader(),
            new Class[]{AccountService.class},
            new InvocationHandler() {
                private AccountService accountService = new AccountServiceImpl();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws SQLException, InvocationTargetException, IllegalAccessException {
                    TransactionAssist.startTransaction();
                    method.invoke(accountService, args);
                    TransactionAssist.commit();
                    return null;
                }
            }
        );
        accountService.transfer(fromActno, toActno, money);
    }

}
