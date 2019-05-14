package com.barstech.financeProject.controllers;


import com.barstech.financeProject.FinanceProjectApplication;
import com.barstech.financeProject.exceptionsUtility.ApiExceptionsHandler;
import com.barstech.financeProject.models.ConfirmationStatus;
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
public class TransactionControllerIntegrationTest {

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
    public void getRequestDateWithTransactionApproved() throws Exception {

        //Mock
        mockMvc.perform(get("/api/transaction-reports?start={startDate}&end={endDate}", "2015-07-01", "2015-10-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value(ConfirmationStatus.APPROVED.toString()));
    }
}
