package com.example.cloud_storage.config;

import com.example.cloud_storage.repository.BlackListTokenRepository;
import com.example.cloud_storage.service.AuthenticationService;
import com.example.cloud_storage.service.JwtProcessingService;
import com.example.cloud_storage.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomLogoutHandlerConfig implements LogoutHandler {
    private final LogoutService logoutService;
    private final JwtProcessingService jwtProcessingService;
    private final UserDetailsService userDetailsService;
    private final BlackListTokenRepository blackListTokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {

            String token = request.getHeader("auth-token");
            String username = jwtProcessingService.extractUsername(token);
            if(jwtProcessingService.isTokenValid(token, userDetailsService.loadUserByUsername(username)) ||
                    !blackListTokenRepository.existsById(token)) {
                System.out.println("--->>> Logout");
                logoutService.exitUser(token);
            } else {
                throw new RuntimeException("Ne valid"); //todo
            }
    }
}
