package com.example.cloud_storage.exception;

public class ErrorUnauthorizedException extends RuntimeException {
    public ErrorUnauthorizedException(String msg) {
        super(msg);
    }
}
