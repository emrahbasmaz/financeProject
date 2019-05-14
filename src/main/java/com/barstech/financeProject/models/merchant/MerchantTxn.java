package com.barstech.financeProject.models.merchant;

import com.barstech.financeProject.models.generic.Agent;

import java.util.HashMap;
import java.util.Map;

public class MerchantTxn {

    private Integer id;
    private String referenceNo;
    private Integer merchantId;
    private Integer fxTxnId;
    private Integer acquirerTxnId;
    private String chainId;
    private Integer agentInfoId;
    private Object returnUrl;
    private String status;
    private String operation;
    private String createdAt;
    private String updatedAt;
    private Object deletedAt;
    private String code;
    private String message;
    private String channel;
    private Object customData;
    private Object parentId;
    private String type;
    private String txnId;
    private Agent agent;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MerchantTxn withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public MerchantTxn withReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        return this;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public MerchantTxn withMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public Integer getFxTxnId() {
        return fxTxnId;
    }

    public void setFxTxnId(Integer fxTxnId) {
        this.fxTxnId = fxTxnId;
    }

    public MerchantTxn withFxTxnId(Integer fxTrxnId) {
        this.fxTxnId = fxTxnId;
        return this;
    }

    public Integer getAcquirerTxnId() {
        return acquirerTxnId;
    }

    public void setAcquirerTxnId(Integer acquirerTxnId) {
        this.acquirerTxnId = acquirerTxnId;
    }

    public MerchantTxn withAcquirerTxnId(Integer acquirerTxnId) {
        this.acquirerTxnId = acquirerTxnId;
        return this;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public MerchantTxn withChainId(String chainId) {
        this.chainId = chainId;
        return this;
    }

    public Integer getAgentInfoId() {
        return agentInfoId;
    }

    public void setAgentInfoId(Integer agentInfoId) {
        this.agentInfoId = agentInfoId;
    }

    public MerchantTxn withAgentInfoId(Integer agentInfoId) {
        this.agentInfoId = agentInfoId;
        return this;
    }

    public Object getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(Object returnUrl) {
        this.returnUrl = returnUrl;
    }

    public MerchantTxn withReturnUrl(Object returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MerchantTxn withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MerchantTxn withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public MerchantTxn withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MerchantTxn withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public MerchantTxn withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MerchantTxn withCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MerchantTxn withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public MerchantTxn withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Object getCustomData() {
        return customData;
    }

    public void setCustomData(Object customData) {
        this.customData = customData;
    }

    public MerchantTxn withCustomData(Object customData) {
        this.customData = customData;
        return this;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public MerchantTxn withParentId(Object parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MerchantTxn withType(String type) {
        this.type = type;
        return this;
    }

    public String getTxnId() {

        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public MerchantTxn withTxnId(String txnId) {
        this.txnId = txnId;
        return this;
    }

    public Agent getAgent() {

        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public MerchantTxn withAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {

        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MerchantTxn withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}

