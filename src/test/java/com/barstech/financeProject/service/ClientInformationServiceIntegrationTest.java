package com.barstech.financeProject.service;

import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.client.*;
import com.barstech.financeProject.services.ClientInformationService;
import com.barstech.financeProject.services.TokenService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientInformationServiceIntegrationTest {
    @Autowired
    TokenService tokenService;

    @Autowired
    private ClientInformationService clientService;
    private String _token;

    @Before
    public void setUp() throws Exception {
        _token = tokenService.getToken();
    }

    @Test
    public void getCustomerInfoWithValidResponse() throws IOException {

        //prepare response data
        ClientInformationResponse clientInformationResponse = JsonUtility.getObjectFrom("clientInfoResponse.json", ClientInformationResponse.class);
        // take prepared data
        Optional<ClientInformationResponse> clientInfo = clientService.getClientInfo(new ClientInformationRequest("1010992-1539329625-1293"), _token);

        assertThat(clientInfo.get().getCustomerInfo())
                .isEqualToComparingFieldByFieldRecursively(clientInformationResponse.getCustomerInfo());
    }
}
