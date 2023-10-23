package com.example.cloud_storage.service;

import com.example.cloud_storage.config.ConfigurationForAbs;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static com.example.cloud_storage.controller.AuthenticationControllerTest.authToken;

@AutoConfigureMockMvc
public class JwtProcessingServiceTest extends ConfigurationForAbs {

    @Autowired
    JwtProcessingService jwtProcessingService;

    @Test
    public void extractUsernameTest() {
        String expected = "bob";

        String result = jwtProcessingService.extractClaim(authToken, Claims::getSubject);

        Assertions.assertEquals(result, expected);
    }
}
