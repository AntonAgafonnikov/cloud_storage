package com.example.cloud_storage.exception;

public class ErrorBadRequest extends RuntimeException {
    public ErrorBadRequest(String msg) {
        super(msg);
    }
}
