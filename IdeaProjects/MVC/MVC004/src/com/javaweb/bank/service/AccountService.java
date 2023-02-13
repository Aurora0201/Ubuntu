package com.javaweb.bank.service;

import com.javaweb.bank.exceptions.AppException;
import com.javaweb.bank.exceptions.MoneyNotEnoughException;

public interface AccountService {
    void transfer(String fromActno, String toActno, double money) throws AppException, MoneyNotEnoughException;
}
