package com.barstech.financeProject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.barstech.financeProject.models.login.*;

import java.util.Optional;

@Service
public class TokenService {
    @Value("${sandbox.client.email}")
    private String clientEmail;

    @Value("${sandbox.client.password}")
    private String clientPassword;

    private LoginService loginService;

    public TokenService(LoginService loginService) {

        this.loginService = loginService;
    }

    private Optional<String> token = Optional.ofNullable(null);

    public String getToken() {
        return token.orElseGet(() -> {
            LoginResponse response = loginService.login(clientEmail, clientPassword).orElseThrow(() -> new RuntimeException(""));
            token = Optional.ofNullable(response.getToken());
            return token.get();
        });
    }

    public void destroyToken() {
        this.token = Optional.ofNullable(null);
    }
}

