package com.musala.gateway.errors.exception;

public class NoMoreDevicesAllowed extends RuntimeException {
    public NoMoreDevicesAllowed() {
        super();
    }

    public NoMoreDevicesAllowed(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoreDevicesAllowed(String message) {
        super(message);
    }

    public NoMoreDevicesAllowed(Throwable cause) {
        super(cause);
    }
}