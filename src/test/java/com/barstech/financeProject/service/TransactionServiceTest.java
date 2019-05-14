package com.barstech.financeProject.service;

import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.transaction.*;
import com.barstech.financeProject.services.LoginService;
import com.barstech.financeProject.services.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionServiceTest {

    @Autowired
    LoginService loginService;
    @MockBean
    private RestTemplate restTemplate;
    @Autowired
    private TransactionService txnService;

    @Test
    public void getDateValuesWithValidResponse() throws IOException {

        //prepare response data
        TxnReportResponse txnReportResponse = JsonUtility.getObjectFrom("transactionReportResponse.json", TxnReportResponse.class);

        //configure behavior of service and take prepared data
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), Matchers.<Class<TxnReportResponse>>any()))
                         .thenReturn(new ResponseEntity<>(txnReportResponse, HttpStatus.OK));
        Optional<TxnReportResponse> responseTransaction = txnService.getTxns(new TxnReportRequest("2015-07-01", "2019-03-01"), null);

        assertThat(responseTransaction).isNotNull().matches(x -> ConfirmationStatus.APPROVED.toString().equals(x.get().getStatus())
                                                    && x.get().getResponse().size() == txnReportResponse.getResponse()
                                                    .size());

        assertThat(responseTransaction.get()).isEqualToComparingFieldByFieldRecursively(txnReportResponse);
    }

    @Test
    public void getDateValuesWithInValidResponse() throws IOException {

        //prepare response data
        TxnReportResponse txnReportResponse = JsonUtility.getObjectFrom("transactionReportResponseInvalid.json", TxnReportResponse.class);

        //configure behavior of service and take prepared data
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), Matchers.<Class<TxnReportResponse>>any()))
                .thenReturn(new ResponseEntity<>(txnReportResponse, HttpStatus.OK));
        Optional<TxnReportResponse> responseTransaction = txnService.getTxns(new TxnReportRequest("", "2019-03-01"), null);

        assertThat(responseTransaction).isNotNull().matches(x -> ConfirmationStatus.DECLINED.toString().equals(x.get().getStatus())
                && x.get().getResponse().size() == txnReportResponse.getResponse()
                .size());

        assertThat(responseTransaction.get()).isEqualToComparingFieldByFieldRecursively(txnReportResponse);
    }

    @Test
    public void getTxnIdWithValidResponse() throws IOException {

        //prepare response data
        TxnResponse txnResponse = JsonUtility.getObjectFrom("transactionResponse.json", TxnResponse.class);

        //configure behavior of service and take prepared data
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), Matchers.<Class<TxnResponse>>any()))
                         .thenReturn(new ResponseEntity<>(txnResponse, HttpStatus.OK));

        Optional<TxnResponse> responseTransaction = txnService.getTxn(new TxnRequest("1010992-1539329625-1293"), null);

        assertThat(responseTransaction.get()).isEqualToComparingFieldByFieldRecursively(txnResponse);
    }

    @Test
    public void getTxnIdWithInValidResponse() throws IOException {

        //prepare response data
        TxnResponse txnResponse = JsonUtility.getObjectFrom("transactionResponseInvalid.json", TxnResponse.class);

        //configure behavior of service and take prepared data
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), Matchers.<Class<TxnResponse>>any()))
                .thenReturn(new ResponseEntity<>(txnResponse, HttpStatus.OK));

        Optional<TxnResponse> responseTransaction = txnService.getTxn(new TxnRequest("1010992-1539329625-1293"), null);

        assertThat(responseTransaction).isNotNull()
                .matches(x -> ConfirmationStatus.DECLINED.toString()
                        .equals(x.get().getTxn().getMerchant().getStatus()));

        assertThat(responseTransaction.get()).isEqualToComparingFieldByFieldRecursively(txnResponse);
    }
}
