package com.barstech.financeProject.models.merchant;

import java.util.HashMap;
import java.util.Map;

public class Merchant {

    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Merchant withName(String name) {
        this.name = name;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {

        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {

        this.additionalProperties.put(name, value);
    }

    public Merchant withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

