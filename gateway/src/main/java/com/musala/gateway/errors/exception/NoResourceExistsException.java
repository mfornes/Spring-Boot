package com.musala.gateway.errors.exception;

public class NoResourceExistsException extends RuntimeException {
    public NoResourceExistsException() {
        super();
    }

    public NoResourceExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResourceExistsException(String message) {
        super(message);
    }

    public NoResourceExistsException(Throwable cause) {
        super(cause);
    }
}
