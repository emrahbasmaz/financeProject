package com.barstech.financeProject.service;

import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.login.LoginResponse;
import com.barstech.financeProject.services.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginServiceIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void getUserDataAfterLoginConfirmed() {

        //Login
        Optional<LoginResponse> loginResponse = loginService.login("demo@bumin.com.tr", "cjaiU8CV");

        assertThat(loginResponse).isNotNull().matches(x -> x.get().getStatus()
                                             .equals(ConfirmationStatus.APPROVED.toString()));
    }

}

