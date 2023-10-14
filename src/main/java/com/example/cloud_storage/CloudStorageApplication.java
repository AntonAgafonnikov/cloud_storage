package com.example.cloud_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class CloudStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStorageApplication.class, args);

//        SecureRandom secureRandom = new SecureRandom();
//        byte[] secretBytes = new byte[36]; //36*8=288 (>256 bits required for HS256)
//        secureRandom.nextBytes(secretBytes);
//        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
//        System.out.println(encoder.encodeToString(secretBytes));
    }

}
