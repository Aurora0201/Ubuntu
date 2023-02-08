package com.javaweb.bank.exceptions;

/**
 * Money not enough exception
 * @author binjunkai
 * @version 2.0
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
