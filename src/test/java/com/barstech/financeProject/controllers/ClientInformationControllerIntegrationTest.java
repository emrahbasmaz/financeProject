package com.barstech.financeProject.controllers;


import com.barstech.financeProject.FinanceProjectApplication;
import com.barstech.financeProject.exceptionsUtility.*;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.client.*;
import com.barstech.financeProject.models.entity.Fx;
import com.barstech.financeProject.models.generic.CustomerInfo;
import com.barstech.financeProject.models.merchant.Merchant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FinanceProjectApplication.class)
public class ClientInformationControllerIntegrationTest {

    @Autowired
    ClientInformationController clientController;
    @Autowired
    ApiExceptionsHandler exceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(this.clientController)
                .setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void getTxnIdWhenCustomerInfoNotFound() throws Exception {

        //mock perform
        this.mockMvc.perform(get("/api/customer-infos/{txnId}", Long.MAX_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    public void getTxnIdWhenCustomerInfoWithValidResponse() throws Exception {
        //prepared response data
        ClientInformationResponse clientInformationResponse = JsonUtility.getObjectFrom("clientInfoResponse.json", ClientInformationResponse.class);
        CustomerInfo info = clientInformationResponse.getCustomerInfo();

        //mock perform
        this.mockMvc.perform(get("/api/customer-infos/{txnId}", clientInformationResponse.getTxn().getMerchant().getTxnId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.customerInfo.id").value(info.getId().intValue()))
                .andExpect(jsonPath("$.customerInfo.number").value(info.getNumber()))
                .andExpect(jsonPath("$.customerInfo.expiryMonth").value(info.getExpiryMonth()))
                .andExpect(jsonPath("$.customerInfo.expiryYear").value(info.getExpiryYear()))
                .andExpect(jsonPath("$.customerInfo.email").value(info.getEmail()))
                .andExpect(jsonPath("$.customerInfo.birthday").value(info.getBirthday()))
                .andExpect(jsonPath("$.customerInfo.gender").value(info.getGender()))
                .andExpect(jsonPath("$.customerInfo.shippingTitle").value(info.getShippingTitle()))
                .andExpect(jsonPath("$.customerInfo.shippingCity").value(info.getShippingCity()))
                .andExpect(jsonPath("$.customerInfo.billingPostcode").value(info.getBillingPostcode()))
                .andExpect(jsonPath("$.customerInfo.shippingPhone").value(info.getShippingPhone()))
                .andExpect(jsonPath("$.customerInfo.billingCity").value(info.getBillingCity())
                );
    }
}
