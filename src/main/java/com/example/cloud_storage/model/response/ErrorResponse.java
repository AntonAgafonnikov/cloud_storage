package com.example.cloud_storage.model.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int id;
}
