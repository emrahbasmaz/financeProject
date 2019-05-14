package com.barstech.financeProject.controllers;

import com.barstech.financeProject.exceptionsUtility.ApiExceptions;
import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.interfaces.IResponsible;
import com.barstech.financeProject.models.transaction.*;
import com.barstech.financeProject.services.TokenService;
import com.barstech.financeProject.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class TransactionController {

    private TransactionService txnService;

    private TokenService tokenService;

    public TransactionController(TransactionService txnService, TokenService tokenService) {
        this.txnService = txnService;
        this.tokenService = tokenService;
    }

    @GetMapping("/transaction-reports")
    public ResponseEntity<TxnReportResponse> getTxns(@RequestParam("start") String startDate, @RequestParam("end") String endDate) {
        Optional<TxnReportResponse> response = txnService.getTxns(new TxnReportRequest(startDate, endDate), tokenService.getToken());
        response.ifPresent(resp -> {
            if (ConfirmationStatus.DENIED.toString().equals(resp.getStatus()))
                throw new ApiExceptions(response.get().getMessage());
        });
        return IResponsible.getResponseEntity(response);
    }

    @GetMapping("/transactions/{txnId}")
    public ResponseEntity<TxnResponse> getTxn(@PathVariable String txnId) {

        Optional<TxnResponse> response = txnService.getTxn(new TxnRequest(txnId), tokenService.getToken());
        response.ifPresent(resp -> {
            if (ConfirmationStatus.DENIED.toString().equals(resp.getClass().getAnnotatedSuperclass()))
                throw new ApiExceptions(response.get().getMessage());
        });
        return IResponsible.getResponseEntity(response);
    }
}
