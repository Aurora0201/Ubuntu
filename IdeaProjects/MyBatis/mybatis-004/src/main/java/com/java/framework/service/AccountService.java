package com.java.framework.service;

import com.java.framework.exception.MoneyNotEnoughException;
import com.java.framework.exception.TransferException;

/**
 * interface AccountService
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {
    /**
     * service transfer
     * @param fromActno transfer-out account
     * @param toActno transfer to account
     * @param money transfer amount
     */
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, TransferException;
}
