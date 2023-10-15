package com.example.cloud_storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler extends RuntimeException{

    @ExceptionHandler(ErrorBadRequest.class)
    public ResponseEntity<String> eiHandler(ErrorBadRequest e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); //400
    }

    @ExceptionHandler(ErrorUnauthorizedException.class)
    public ResponseEntity<String> euHandler(ErrorUnauthorizedException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED); //401
    }

    @ExceptionHandler(ErrorInternalServerError.class)
    public ResponseEntity<String> ecHandler(ErrorInternalServerError e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); //500
    }
}
