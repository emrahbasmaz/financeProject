package com.barstech.financeProject.controllers;

import com.barstech.financeProject.FinanceProjectApplication;
import com.barstech.financeProject.exceptionsUtility.ApiExceptionsHandler;
import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.entity.Fx;
import com.barstech.financeProject.models.generic.CustomerInfo;
import com.barstech.financeProject.models.merchant.MerchantTxn;
import com.barstech.financeProject.models.transaction.*;
import com.barstech.financeProject.services.TokenService;
import com.barstech.financeProject.services.TransactionService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinanceProjectApplication.class)
public class TransactionControllerTest {
    @MockBean
    TokenService tokenService;
    @MockBean
    TransactionService transactionService;

    @Autowired
    TransactionController transactionController;
    @Autowired
    ApiExceptionsHandler exceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.transactionController)
                .setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void getTxnIdWithTokenWhenNotFound() throws Exception {
        //set response status
        TxnResponse txnResponse = new TxnResponse();
        txnResponse.setStatus("NOT_FOUND");

        //scenario apply
        when(tokenService.getToken()).thenReturn("token");
        when(transactionService.getTxn(any(TxnRequest.class), any(String.class))).thenReturn(Optional.ofNullable(txnResponse));

        //mock perform
        this.mockMvc.perform(get("/api/transactions/{txnId}", "1010992-1539329625-1293"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    public void getRequestDateWithTokenWhenTransactionApproved() throws Exception {
        //prepare response
        TxnReportResponse txnReportResponse = JsonUtility.getObjectFrom("transactionReportResponse.json", TxnReportResponse.class);

        //scenario getToken && getTxns
        when(tokenService.getToken()).thenReturn("Token");
        when(transactionService.getTxns(any(TxnReportRequest.class), any(String.class))).thenReturn(Optional.ofNullable(txnReportResponse));

        //mock perform
        this.mockMvc.perform(get("/api/transaction-reports?start={startDate}&end={endDate}", "2015-07-01", "2015-10-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value(ConfirmationStatus.APPROVED.toString()));
    }

    @Test
    public void getTxnIdWithTokenAndValidResponse() throws Exception {
        //prepare response
        TxnResponse txnResponse = JsonUtility.getObjectFrom("transactionResponse.json", TxnResponse.class);

        //scenario getToken && getTxn
        when(tokenService.getToken()).thenReturn("Token");
        when(transactionService.getTxn(any(TxnRequest.class), any(String.class))).thenReturn(Optional.ofNullable(txnResponse));

        MerchantTxn merchant = txnResponse.getTxn().getMerchant();
        Fx fx = txnResponse.getFx();
        CustomerInfo customerInfo = txnResponse.getCustomerInfo();

        //mock perform
        this.mockMvc.perform(get("/api/transactions/{txnId}", "1010992-1539329625-1293"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.txn.merchant.id").value(merchant.getId().intValue()))
                .andExpect(jsonPath("$.txn.merchant.referenceNo").value(merchant.getReferenceNo()))
                .andExpect(jsonPath("$.txn.merchant.status").value(merchant.getStatus()))
                .andExpect(jsonPath("$.txn.merchant.operation").value(merchant.getOperation()))
                .andExpect(jsonPath("$.txn.merchant.type").value(merchant.getType()))
                .andExpect(jsonPath("$.txn.merchant.txnId").value(merchant.getTxnId()))
                .andExpect(jsonPath("$.customerInfo.number").value(customerInfo.getNumber()))
                .andExpect(jsonPath("$.customerInfo.expiryMonth").value(customerInfo.getExpiryMonth()))
                .andExpect(jsonPath("$.customerInfo.expiryYear").value(customerInfo.getExpiryYear()))
                .andExpect(jsonPath("$.customerInfo.email").value(customerInfo.getEmail()))
                .andExpect(jsonPath("$.customerInfo.birthday").value(customerInfo.getBirthday()))
                .andExpect(jsonPath("$.customerInfo.gender").value(customerInfo.getGender()))
                .andExpect(jsonPath("$.customerInfo.shippingTitle").value(customerInfo.getShippingTitle()))
                .andExpect(jsonPath("$.customerInfo.shippingCity").value(customerInfo.getShippingCity()))
                .andExpect(jsonPath("$.fx.merchant.originalAmount").value(fx.getMerchant().getOriginalAmount()))
                .andExpect(jsonPath("$.fx.merchant.originalCurrency").value(fx.getMerchant().getOriginalCurrency())
                );
    }

}
