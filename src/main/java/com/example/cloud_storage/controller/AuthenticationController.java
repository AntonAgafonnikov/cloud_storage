package com.example.cloud_storage.controller;

import com.example.cloud_storage.model.AuthenticationRequest;
import com.example.cloud_storage.model.AuthenticationResponse;
import com.example.cloud_storage.model.RegisterRequest;
import com.example.cloud_storage.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> entryUser(@RequestBody AuthenticationRequest request) {
        System.out.println("--->>> Авторизация пользователя: " + request.getLogin());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


//    @PostMapping("/logout")
//    public void exitUser(@RequestHeader("auth-token") String token) {
//        authenticationService.exitUser(token));
//    }
//
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
//
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
//        return ResponseEntity.ok(authenticationService.authenticate(request));
//    }
}
