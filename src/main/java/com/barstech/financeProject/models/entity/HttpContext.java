package com.barstech.financeProject.models.entity;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class HttpContext {
    public static HttpEntity<?> getHttpEntity(Object request, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(request, headers);
    }
}