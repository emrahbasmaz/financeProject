package com.barstech.financeProject.services;

import com.barstech.financeProject.models.login.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class LoginService {
    private RestTemplate restTemplate;

    @Value("${sandbox.client.url}")
    private String reportingServerUrl;

    public LoginService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public Optional<LoginResponse> login(String userName, String password) {
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(reportingServerUrl + "/v3/merchant/user/login", new LoginRequest(userName, password), LoginResponse.class);
        return Optional.ofNullable(response.getBody());
    }
}
