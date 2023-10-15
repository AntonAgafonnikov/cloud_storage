package com.example.cloud_storage.exception;

public class ErrorInternalServerError extends RuntimeException{
    public ErrorInternalServerError(String msg) {
        super(msg);
    }
}
