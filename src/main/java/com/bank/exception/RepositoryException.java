package com.bank.exception;

import java.io.Serializable;

public class RepositoryException extends RuntimeException implements Serializable {

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
