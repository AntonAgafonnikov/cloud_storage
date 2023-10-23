package com.example.cloud_storage.controller;

import com.example.cloud_storage.model.request.AuthenticationRequest;
import com.example.cloud_storage.model.response.AuthenticationResponse;
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


    @PostMapping("/testpost")
    public void tesPost() {
        System.out.println("--->>> Post" );
    }
}
