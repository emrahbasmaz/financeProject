package com.barstech.financeProject.controllers;

import com.barstech.financeProject.FinanceProjectApplication;
import com.barstech.financeProject.exceptionsUtility.ApiExceptionsHandler;
import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.client.*;
import com.barstech.financeProject.models.entity.Fx;
import com.barstech.financeProject.models.generic.CustomerInfo;
import com.barstech.financeProject.models.merchant.Merchant;
import com.barstech.financeProject.services.ClientInformationService;
import com.barstech.financeProject.services.TokenService;

import java.util.Optional;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinanceProjectApplication.class)
public class ClientInformationControllerTest {
    @MockBean
    TokenService tokenService;
    @MockBean
    ClientInformationService clientService;

    @Autowired
    ClientInformationController clientController;
    @Autowired
    ApiExceptionsHandler exceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.clientController)
                .setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void getTxnIdWithTokenWhenCustomerInfoNotFound() throws Exception {

        ClientInformationResponse clientInfoResponse = new ClientInformationResponse();
        clientInfoResponse.setStatus(ConfirmationStatus.DENIED.toString());

        //scenario
        when(tokenService.getToken()).thenReturn("Token");
        when(clientService.getClientInfo(any(ClientInformationRequest.class), anyString())).thenReturn(Optional.ofNullable(clientInfoResponse));

        //mock perform
        mockMvc.perform(get("/api/customer-infos/{txnId}", Long.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status")
                        .value("NOT_FOUND"));
    }

    @Test
    public void getTxnIdWithTokenWhenCustomerInfoWithValidResponse() throws Exception {
        //prepared response data
        ClientInformationResponse clientInformationResponse = JsonUtility.getObjectFrom("clientInfoResponse.json", ClientInformationResponse.class);

        //scenario
        when(tokenService.getToken()).thenReturn("Token");
        when(clientService.getClientInfo(any(ClientInformationRequest.class), anyString())).thenReturn(Optional.ofNullable(clientInformationResponse));
        CustomerInfo customerInfo = clientInformationResponse.getCustomerInfo();
        Merchant merchant = clientInformationResponse.getMerchant();
        Fx fx = clientInformationResponse.getFx();

        //mock perform
        mockMvc.perform(get("/api/customer-infos/{txnId}", clientInformationResponse.getTxn().getMerchant().getTxnId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.customerInfo.id").value(customerInfo.getId().intValue()))
                .andExpect(jsonPath("$.customerInfo.number").value(customerInfo.getNumber()))
                .andExpect(jsonPath("$.customerInfo.expiryMonth").value(customerInfo.getExpiryMonth()))
                .andExpect(jsonPath("$.customerInfo.expiryYear").value(customerInfo.getExpiryYear()))
                .andExpect(jsonPath("$.customerInfo.email").value(customerInfo.getEmail()))
                .andExpect(jsonPath("$.customerInfo.birthday").value(customerInfo.getBirthday()))
                .andExpect(jsonPath("$.customerInfo.gender").value(customerInfo.getGender()))
                .andExpect(jsonPath("$.customerInfo.shippingTitle").value(customerInfo.getShippingTitle()))
                .andExpect(jsonPath("$.customerInfo.shippingCity").value(customerInfo.getShippingCity()))
                .andExpect(jsonPath("$.customerInfo.billingPostcode").value(customerInfo.getBillingPostcode()))
                .andExpect(jsonPath("$.customerInfo.shippingPhone").value(customerInfo.getShippingPhone()))
                .andExpect(jsonPath("$.customerInfo.billingCity").value(customerInfo.getBillingCity()))
                .andExpect(jsonPath("$.fx.merchant.originalAmount").value(fx.getMerchant().getOriginalAmount()))
                .andExpect(jsonPath("$.fx.merchant.originalCurrency").value(fx.getMerchant().getOriginalCurrency()))
                .andExpect(jsonPath("$.merchant.name").value(merchant.getName())
                );


    }
}
