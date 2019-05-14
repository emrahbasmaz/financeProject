package com.barstech.financeProject.services;

import com.barstech.financeProject.models.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.barstech.financeProject.models.entity.HttpContext.getHttpEntity;

@Service
public class ClientInformationService {
    private RestTemplate restTemplate;

    @Value("${sandbox.client.url}")
    private String reportingServerUrl;

    public ClientInformationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<ClientInformationResponse> getClientInfo(ClientInformationRequest request, String token) {
        HttpEntity<?> httpEntity = getHttpEntity(request, token);
        ClientInformationResponse response = restTemplate.postForObject(reportingServerUrl + "/v3/client", httpEntity, ClientInformationResponse.class);
        return Optional.ofNullable(response);
    }
}