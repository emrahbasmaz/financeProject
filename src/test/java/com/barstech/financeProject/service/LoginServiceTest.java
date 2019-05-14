package com.barstech.financeProject.service;

import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.login.*;
import com.barstech.financeProject.services.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void getUserDataAfterLoginConfirmed() {

        //configure scenario
        when(restTemplate.postForEntity(any(String.class), new LoginRequest(any(), any()), LoginResponse.class))
                                        .thenReturn(new ResponseEntity(new LoginResponse("123", ConfirmationStatus.APPROVED.toString()), HttpStatus.OK));

        //take prepared response data
        Optional<LoginResponse> loginResponse = loginService.login("", "");
        assertThat(loginResponse).isNotNull().matches(x -> x.get().getStatus()
                                             .equals(ConfirmationStatus.APPROVED.toString()));
    }

    @Test
    public void getUserDataAfterLoginDenied() {
        //configure scenario
        when(restTemplate.postForEntity(any(String.class), new LoginRequest(any(), any()), LoginResponse.class))
                                        .thenReturn(new ResponseEntity(new LoginResponse("123", ConfirmationStatus.DENIED.toString()), HttpStatus.OK));

        //take prepared response data
        Optional<LoginResponse> loginResponse = loginService.login("", "");
        assertThat(loginResponse).isNotNull().matches(x -> x.get().getStatus()
                                             .equals(ConfirmationStatus.DENIED.toString()));
    }

}


