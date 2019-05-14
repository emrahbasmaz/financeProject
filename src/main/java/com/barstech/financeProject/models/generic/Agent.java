package com.barstech.financeProject.models.generic;


import java.util.HashMap;
import java.util.Map;

public class Agent {

    private Integer id;
    private String customerIp;
    private String customerUserAgent;
    private String merchantIp;
    private String merchantUserAgent;
    private String createdAt;
    private String updatedAt;
    private Object deletedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Agent withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public Agent withCustomerIp(String customerIp) {
        this.customerIp = customerIp;
        return this;
    }

    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    public void setCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
    }

    public Agent withCustomerUserAgent(String customerUserAgent) {
        this.customerUserAgent = customerUserAgent;
        return this;
    }

    public String getMerchantIp() {
        return merchantIp;
    }

    public void setMerchantIp(String merchantIp) {
        this.merchantIp = merchantIp;
    }

    public Agent withMerchantIp(String merchantIp) {
        this.merchantIp = merchantIp;
        return this;
    }

    public String getMerchantUserAgent() {
        return merchantUserAgent;
    }

    public void setMerchantUserAgent(String merchantUserAgent) {
        this.merchantUserAgent = merchantUserAgent;
    }

    public Agent withMerchantUserAgent(String merchantUserAgent) {
        this.merchantUserAgent = merchantUserAgent;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Agent withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Agent withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Agent withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Agent withAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}


