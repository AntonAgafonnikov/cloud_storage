package com.example.cloud_storage.service;

import com.example.cloud_storage.model.JwtToken;
import com.example.cloud_storage.repository.BlackListTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final BlackListTokenRepository blackListTokenRepository;

    public void exitUser(String token) {
        blackListTokenRepository.save(new JwtToken(token));
    }
}
