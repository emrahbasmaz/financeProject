package com.barstech.financeProject.models.transaction;

import java.util.HashMap;
import java.util.Map;

import com.barstech.financeProject.models.merchant.MerchantTxn;

public class Txn {

    private MerchantTxn merchant;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Txn() {

    }

    public MerchantTxn getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantTxn merchant) {
        this.merchant = merchant;
    }

    public Txn withMerchant(MerchantTxn merchant) {
        this.merchant = merchant;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Txn withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}


