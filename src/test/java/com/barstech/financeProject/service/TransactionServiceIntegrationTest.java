package com.barstech.financeProject.service;

import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.JsonUtility;
import com.barstech.financeProject.models.transaction.*;
import com.barstech.financeProject.services.TokenService;
import com.barstech.financeProject.services.TransactionService;
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
public class TransactionServiceIntegrationTest {

    @Autowired
    TransactionService txnService;

    @Autowired
    TokenService tokenService;

    private String token;

    @Before
    public void setUp() throws Exception {
        this.token = tokenService.getToken();
    }

    @Test
    public void getApprovedWithRequestDate() {

        Optional<TxnReportResponse> txnReportResponse = txnService.getTxns(new TxnReportRequest("2015-07-01", "2019-03-01"), this.token);
        assertThat(txnReportResponse).isNotNull()
                                     .matches(x -> ConfirmationStatus.APPROVED.toString()
                                             .equals(x.get().getStatus()));
    }

    @Test
    public void getValidResponseWithTransactionId() throws IOException {

        //prepare response data
        TxnResponse txnResponse = JsonUtility.getObjectFrom("transactionResponse.json", TxnResponse.class);

        // get prepared response data
        Optional<TxnResponse> resp = txnService.getTxn(new TxnRequest(txnResponse.getTxn().getMerchant().getTxnId()), this.token);
        //

        assertThat(resp.get().getCustomerInfo())
                             .isEqualToComparingFieldByFieldRecursively(txnResponse.getCustomerInfo());
    }

}

