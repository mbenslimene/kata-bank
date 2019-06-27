package com.bank.exception;

import java.io.Serializable;

public class ServiceException extends RuntimeException implements Serializable {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
