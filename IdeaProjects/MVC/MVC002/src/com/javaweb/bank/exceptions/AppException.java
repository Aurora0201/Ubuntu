package com.javaweb.bank.exceptions;

/**
 * Application Exception
 * @author binjunkai
 * @version 2.0
 * @since 1.0
 */
public class AppException extends Exception{
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
