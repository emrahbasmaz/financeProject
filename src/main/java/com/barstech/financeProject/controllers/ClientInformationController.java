package com.barstech.financeProject.controllers;

import com.barstech.financeProject.exceptionsUtility.ApiExceptions;
import com.barstech.financeProject.models.ConfirmationStatus;
import com.barstech.financeProject.models.client.ClientInformationRequest;
import com.barstech.financeProject.models.client.ClientInformationResponse;
import com.barstech.financeProject.models.interfaces.IResponsible;
import com.barstech.financeProject.services.ClientInformationService;
import com.barstech.financeProject.services.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientInformationController {

    private ClientInformationService clientService;
    private TokenService tokenService;

    public ClientInformationController(ClientInformationService clientService, TokenService tokenService) {
        this.clientService = clientService;
        this.tokenService = tokenService;
    }

    @GetMapping("/customer-infos/{txnId}")
    public ResponseEntity<ClientInformationResponse> getClientInformation(@PathVariable String txnId) {
        String token = this.tokenService.getToken();
        Optional<ClientInformationResponse> client = this.clientService.getClientInfo(new ClientInformationRequest(txnId), token);

        client.ifPresent(resp -> {
            if (ConfirmationStatus.DENIED.toString().equals(resp.getStatus()))
                throw new ApiExceptions(client.get().getMessage());
        });
        return IResponsible.getResponseEntity(client);
    }
}
