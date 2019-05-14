package com.barstech.financeProject.models.merchant;

import java.util.HashMap;
import java.util.Map;

public class MerchantFx {

    private Integer originalAmount;
    private String originalCurrency;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getOriginalAmount() {

        return originalAmount;
    }

    public void setOriginalAmount(Integer originalAmount) {

        this.originalAmount = originalAmount;
    }

    public MerchantFx withOriginalAmount(Integer originalAmount) {
        this.originalAmount = originalAmount;
        return this;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {

        this.originalCurrency = originalCurrency;
    }

    public MerchantFx withOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {

        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {

        this.additionalProperties.put(name, value);
    }

    public MerchantFx withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
