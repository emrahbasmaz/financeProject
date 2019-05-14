package com.barstech.financeProject.services;

import com.barstech.financeProject.models.transaction.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.barstech.financeProject.models.entity.HttpContext.getHttpEntity;

import java.util.Optional;

@Service
public class TransactionService {

    @Value("${sandbox.client.url}")
    private String reportingServerUrl;

    RestTemplate restTemplate;

    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<TxnReportResponse> getTxns(TxnReportRequest txnReportRequest, String token) {
        HttpEntity<?> httpEntity = getHttpEntity(txnReportRequest, token);
        ResponseEntity<TxnReportResponse> response = restTemplate.postForEntity(reportingServerUrl + "/v3/transactions/report", httpEntity, TxnReportResponse.class);
        return Optional.ofNullable(response.getBody());
    }

    public Optional<TxnResponse> getTxn(TxnRequest request, String token) {
        HttpEntity<?> httpEntity = getHttpEntity(request, token);
        ResponseEntity<TxnResponse> response = restTemplate.postForEntity(reportingServerUrl + "/v3/transaction", httpEntity, TxnResponse.class);
        return Optional.ofNullable(response.getBody());
    }
}
