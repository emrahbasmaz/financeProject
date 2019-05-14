package com.barstech.financeProject.models.client;

import com.barstech.financeProject.models.entity.Fx;
import com.barstech.financeProject.models.generic.CustomerInfo;
import com.barstech.financeProject.models.merchant.Merchant;
import com.barstech.financeProject.models.transaction.Txn;

import java.util.HashMap;
import java.util.Map;

public class ClientInformationResponse {
    private String status;
    private String message;
    private CustomerInfo customerInfo;
    private Fx fx;
    private Txn txn;
    private Merchant merchant;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Fx getFx() {
        return fx;
    }

    public void setFx(Fx fx) {
        this.fx = fx;
    }

    public Txn getTxn() {
        return txn;
    }

    public void setTxn(Txn txn) {
        this.txn = txn;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

