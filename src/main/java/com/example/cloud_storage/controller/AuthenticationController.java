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
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
