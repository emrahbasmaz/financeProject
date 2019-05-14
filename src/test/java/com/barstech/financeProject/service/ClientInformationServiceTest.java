package com.barstech.financeProject.service;

import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.client.*;
import com.barstech.financeProject.services.ClientInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
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
public class ClientInformationServiceTest {

    @MockBean
    RestTemplate restTemplate;
    @Autowired
    private ClientInformationService clientService;

    @Test
    public void getClientInfoWithValidResponse() throws IOException {
        //prepare response data
        ClientInformationResponse clientInformationResponse = JsonUtility.getObjectFrom("clientInfoResponse.json", ClientInformationResponse.class);

        //configure behavior of service
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any())).thenReturn(clientInformationResponse);

        Optional<ClientInformationResponse> clientInformation = clientService.getClientInfo(new ClientInformationRequest(""), "");

        assertThat(clientInformation.get())
                    .isEqualToComparingFieldByFieldRecursively(clientInformationResponse);
    }

    @Test
    public void getClientInfoWithInValidResponse() throws IOException {
        //prepare response data
        ClientInformationResponse clientInformationResponse = JsonUtility.getObjectFrom("customerInfoResponse.json", ClientInformationResponse.class);

        //configure behavior of service
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any())).thenReturn(clientInformationResponse);

        Optional<ClientInformationResponse> clientInformation = clientService.getClientInfo(new ClientInformationRequest(""), "");

        assertThat(clientInformation).isNotNull().matches(x -> ConfirmationStatus.DENIED.toString().equals(x.get().getTxn().getMerchant().getStatus()));

        assertThat(clientInformation.get())
                .isEqualToComparingFieldByFieldRecursively(clientInformationResponse);
    }

}

