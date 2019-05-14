package com.barstech.financeProject.models.transaction;

import com.barstech.financeProject.models.entity.Fx;
import com.barstech.financeProject.models.generic.CustomerInfo;
import com.barstech.financeProject.models.merchant.Merchant;

import java.util.HashMap;
import java.util.Map;

public class TxnResponse {

    private String status;
    private String message;
    private CustomerInfo customerInfo;
    private Fx fx;
    private Txn txn;
    private Merchant merchant;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public TxnResponse() {
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

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public TxnResponse withCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
        return this;
    }

    public Fx getFx() {
        return fx;
    }

    public void setFx(Fx fx) {
        this.fx = fx;
    }

    public TxnResponse withFx(Fx fx) {
        this.fx = fx;
        return this;
    }

    public Txn getTxn() {
        return txn;
    }

    public void setTxn(Txn txn) {
        this.txn = txn;
    }

    public TxnResponse withTransaction(Txn txn) {
        this.txn = txn;
        return this;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public TxnResponse withMerchant(Merchant merchant) {
        this.merchant = merchant;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public TxnResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}