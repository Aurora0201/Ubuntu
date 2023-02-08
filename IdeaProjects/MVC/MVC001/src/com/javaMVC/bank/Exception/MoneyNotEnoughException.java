package com.javaMVC.bank.Exception;

/**
 * MoneyNotEnoughException
 * @author binjunkai
 * @version 1.0
 * @since 1.0
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
        super();
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
